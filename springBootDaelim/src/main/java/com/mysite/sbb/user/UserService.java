package com.mysite.sbb.user;

import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public boolean loginExecute(LoginVO loginVO){
		String input_sha256U;
		String input_sha256L;
		String input = loginVO.getPassword().trim();
		String Loginchk = null;

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			input_sha256U = hexString.toString().toUpperCase();
			input_sha256L = hexString.toString().toLowerCase();

		} catch (Exception ex) {

			throw new RuntimeException(ex);
		}
		boolean result = false;
		try {
			Loginchk = userRepository.login(loginVO.getUserid(), input_sha256U, input_sha256L);
			if (Loginchk == null) {

				result = false;

			} else {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
