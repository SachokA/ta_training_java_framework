package framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

enum ApplicationProperties {
    BASE_URL("base.url"),
    BROWSER_NAME("browser.name"),
    NUMBER_OF_INSTANCES ("testdata.number_of_instances"),
    OPERATION_SYSTEM("testdata.operation_system"),
    VM_CLASS ("testdata.vm_class"),
    INSTANCE_SERIES ("testdata.instance_series"),
    INSTANCE_TYPE ( "testdata.instance_type"),
    NUMBER_OF_GPUS ( "testdata.number_of_gpus"),
    GPU_TYPE ( "testdata.gpu_type"),
    LOCAL_SSD ("testdata.local_ssd"),
    DATACENTER_LOCATION ("testdata.datacenter_location");
    //
    private String propertyName;

    private ApplicationProperties(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public String toString() {
        return propertyName;
    }
}

public class PropertiesUtils {
    public static final String ERROR_READ_PROPERTY = "Error, property not found";
    //
    private static final String DEFAULT_FILENAME = "dev.properties";
    private final String PATH_SEPARATOR = "/";
    //
    private static volatile PropertiesUtils instance = null;
    //
    private Properties appProps;
    private String filename;

    private PropertiesUtils(String filename) {
        this.filename = filename;
        init();
    }

    public static PropertiesUtils get() {
        return get(DEFAULT_FILENAME);
    }

    public static PropertiesUtils get(String filename) {
        if (instance == null) {
            synchronized (PropertiesUtils.class) {
                if (instance == null) {
                    instance = new PropertiesUtils(filename);
                }
            }
        }
        return instance;
    }

    private void init() {
        appProps = new Properties();
        String filePath = getFullPath();
        loadProperties(filePath);
    }

    private String getFullPath() {
        String path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath();
        return path;
    }

    private void loadProperties(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            appProps.load(fileInputStream);
        } catch (Exception e) {
            // log
            System.out.println("ERROR Reading " + filePath + "  Message = " + e.getMessage());

        }
    }

    public String readProperty(String propertyName) {
        return appProps.getProperty(propertyName, ERROR_READ_PROPERTY);
    }

    public String readBaseUrl() {
        return readProperty(ApplicationProperties.BASE_URL.getPropertyName());
    }

    public String readBrowserName() {
        return readProperty(ApplicationProperties.BROWSER_NAME.getPropertyName());
    }

    public String readNumberOfInstances() {
        return readProperty(ApplicationProperties.NUMBER_OF_INSTANCES.getPropertyName());
    }

    public String readOperatingSystem() {
        return readProperty(ApplicationProperties.OPERATION_SYSTEM.getPropertyName());
    }

    public String readVmClass() {
        return readProperty(ApplicationProperties.VM_CLASS.getPropertyName());
    }

    public String readInstanceSeries() {
        return readProperty(ApplicationProperties.INSTANCE_SERIES.getPropertyName());
    }

    public String readInstanceType() {
        return readProperty(ApplicationProperties.INSTANCE_TYPE.getPropertyName());
    }

    public String readNumberOfGpus() {
        return readProperty(ApplicationProperties.NUMBER_OF_GPUS.getPropertyName());
    }

    public String readGpuType() {
        return readProperty(ApplicationProperties.GPU_TYPE.getPropertyName());
    }

    public String readLocalSsd() {
        return readProperty(ApplicationProperties.LOCAL_SSD.getPropertyName());
    }

    public String readDatacenterLocation() {
        return readProperty(ApplicationProperties.DATACENTER_LOCATION.getPropertyName());
    }

}
