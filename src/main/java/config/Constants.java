package config;

import org.aeonbits.owner.ConfigFactory;

public class Constants {
    private static DataConfig dataConfig = ConfigFactory.newInstance().create(DataConfig.class);

    public static final String URL = dataConfig.getUrl();
}
