package br.com.anodes.apifaceframe.utils;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

    public static String gerarBCrypt(final String senha) {
        if (Strings.isNullOrEmpty(senha)) {
            return senha;
        }

        log.info("Gerado hash com o BCryp");
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }
}
