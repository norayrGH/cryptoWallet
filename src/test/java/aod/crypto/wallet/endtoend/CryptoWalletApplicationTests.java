package aod.crypto.wallet.endtoend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
class CryptoWalletApplicationTests {

    @Autowired
    private WebTestClient webClient;

/*
    @Autowired
    private WalletRepository walletRepository;

    @Test
    void getWalletInfo() {
        WalletInfo norayr = walletRepository.save(WalletInfo.of("norayr")).subscribeOn(Schedulers.immediate()).block();

        this.webClient.get()
                .uri("wallet/{id}", 1L)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WalletInfoDTO.class)
                .consumeWith(walletInfo -> Assertions.assertEquals(Objects.requireNonNull(norayr).getWalletOwnerName(), Objects.requireNonNull(walletInfo.getResponseBody()).getOwnerName()) );
    }
*/

}
