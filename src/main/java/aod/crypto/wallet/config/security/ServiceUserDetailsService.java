package aod.crypto.wallet.config.security;

import aod.crypto.wallet.config.security.custom.CustomUser;
import aod.crypto.wallet.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ServiceUserDetailsService implements UserDetailsService {

    private final UserService users;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        var walletUser = users.findByEmail(username).orElseThrow(() -> {
            log.error("Can`t authorize user. Username {} not found.", username);
            return new UsernameNotFoundException("Can`t authorize user. Username not found." + username);
        });
        return new CustomUser(walletUser);
    }
}
