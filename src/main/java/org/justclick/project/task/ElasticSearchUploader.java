package org.justclick.project.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.justclick.project.document.LogRegistry;
import org.justclick.project.nested.Cookie;
import org.justclick.project.nested.Header;
import org.justclick.project.nested.Param;
import org.justclick.project.service.LogRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ua_parser.Client;
import ua_parser.Parser;

@Component
public class ElasticSearchUploader {
    private String logsPath;
    private Environment env;
    private LogRegistryService logRegistryService;
    
    private Logger logger = LogManager.getLogger(ElasticSearchUploader.class);

    @Autowired
    public ElasticSearchUploader(Environment env, LogRegistryService logRegistryService) {
        this.env = env;
        this.logsPath = this.env.getProperty("logging.path");
        this.logRegistryService = logRegistryService;
    }

    @Scheduled(cron="0 * * * * *")
    public void uploadLogs(){
        createRegistries();
        cleanLogFile();
    }

    private void cleanLogFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(this.logsPath);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createRegistries(){
        BufferedReader reader = null;
        try {
            List<LogRegistry> registries = new ArrayList<>();
            reader = new BufferedReader(new FileReader(this.logsPath));
            while (reader.ready()) {
                String line = reader.readLine();
                LogRegistry entry = this.getLog(line);
                if(entry != null){
                    registries.add(entry);
                }
            }
            this.logRegistryService.saveAll(registries);
            logger.info(String.format("Saved %d entries!", registries.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private LogRegistry getLog(String line) {
        try {
            String log = line.trim();
            JSONObject logEntry = new JSONObject(log);
            String message = logEntry.getString("message");
            JSONObject logContext = new JSONObject(message);
            JSONObject headers = logContext.getJSONObject("headers");
            String userAgent = headers.getString("user-agent");

            Parser parser = new Parser();
            Client client = parser.parse(userAgent);
            return new LogRegistry(
                log, client.os.family, client.os.major, client.userAgent.family, client.userAgent.major,
                this.getCookies(logContext), this.getHeaders(logContext), this.getParams(logContext)
            );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Cookie> getCookies(JSONObject logContext){
        JSONObject logCookies = logContext.getJSONObject("cookies");
        List<Cookie> cookies = new ArrayList<>(); 
        Iterator<String> iterator = logCookies.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            cookies.add(new Cookie(key, logCookies.getString(key)));
        }
        return cookies;
    }

    private List<Header> getHeaders(JSONObject logContext){
        JSONObject logHeaders = logContext.getJSONObject("headers");
        List<Header> headers = new ArrayList<>(); 
        Iterator<String> iterator = logHeaders.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            headers.add(new Header(key, logHeaders.getString(key)));
        }
        return headers;
    }

    private List<Param> getParams(JSONObject logContext){
        JSONObject logParams = logContext.getJSONObject("params");
        List<Param> params = new ArrayList<>(); 
        Iterator<String> iterator = logParams.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            params.add(new Param(key, logParams.getString(key)));
        }
        return params;
    }
}
