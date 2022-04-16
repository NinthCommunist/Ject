package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:properties/ui/application.properties"})
public interface DataConfig extends Config {

    @Key("ui_url")
    String getUiUrl();

    @Key("ui_timeout")
    Integer getUiTimeout();

}
