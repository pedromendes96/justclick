package org.justclick.project.document;

import java.util.Date;
import java.util.List;

import org.justclick.project.nested.Cookie;
import org.justclick.project.nested.Header;
import org.justclick.project.nested.Param;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "#{@environment.getProperty('elasticsearch.log-registry.index.name')}")
public class LogRegistry {
    @Id
    private String id;

    @Field(type = FieldType.Date)
    private Date timestamp;

    @Field(type = FieldType.Text)
    private String log;

    @Field(type = FieldType.Keyword)
    String operatingSystem;

    @Field(type = FieldType.Keyword)
    String operatingSystemVersion;

    @Field(type = FieldType.Keyword)
    String browser;
    
    @Field(type = FieldType.Keyword)
    String browserVersion;

    @Field( type = FieldType.Nested)
    private List<Cookie> cookies;

    @Field( type = FieldType.Nested)
    private List<Header> headers;

    @Field( type = FieldType.Nested)
    private List<Param> params;

    public LogRegistry(String log, String operatingSystem, String operatingSystemVersion, String browser,
            String browserVersion, List<Cookie> cookies, List<Header> headers, List<Param> params) {
        this.log = log;
        this.timestamp = new Date();
        this.operatingSystem = operatingSystem;
        this.operatingSystemVersion = operatingSystemVersion;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.cookies = cookies;
        this.headers = headers;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    
}
