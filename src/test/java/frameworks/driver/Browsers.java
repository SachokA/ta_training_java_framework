package frameworks.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

    interface Browser {
        WebDriver getBrowser(); // Factory method
    }

    class FirefoxTemporary implements Browser {
        public WebDriver getBrowser() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }

    class EdgeTemporary implements Browser {
        public WebDriver getBrowser() {
          WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
        }
     }

    class FirefoxWithoutUI implements Browser {
        public WebDriver getBrowser() {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            return new FirefoxDriver(options);
        }
    }

    class ChromeTemporary implements Browser {
        public WebDriver getBrowser() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

    class ChromeWithoutUI implements Browser {
        public WebDriver getBrowser() {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            return new ChromeDriver(options);
        }
    }

    public enum Browsers {
        DEFAULT_TEMPORARY(new ChromeTemporary()),
        FIREFOX_TEMPORARY(new FirefoxTemporary()),
        FIREFOX_WITHOUTUI(new FirefoxWithoutUI()),
        CHROME_TEMPORARY(new ChromeTemporary()),
        EDGE_TEMPORARY(new EdgeTemporary()),
        CHROME_WITHOUTUI(new ChromeWithoutUI());

        private Browser browser;

        private Browsers(Browser browser) {
            this.browser = browser;
        }

        public WebDriver runBrowser() {
            WebDriver driver = browser.getBrowser();
            driver.manage().window().maximize();
            return driver;
        }
    }

