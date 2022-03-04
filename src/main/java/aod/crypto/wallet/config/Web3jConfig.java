package aod.crypto.wallet.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Slf4j
@Configuration
public class Web3jConfig {


    @Value("${ethereum.service.url}")
    private String url;

    @Bean
    public Web3j getEthereumConnection() {

        Web3j web3j = Web3j.build(new HttpService(url));
        Web3ClientVersion web3ClientVersion;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
            log.info("Info about web3j client {}", web3ClientVersion.getResult());
        } catch (IOException e) {
            log.info("Can`t load web3j client version {}", e.getMessage());
        }
        return web3j;
    }
}
