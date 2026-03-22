package com.walletwise;

import org.springframework.boot.SpringApplication;

public class TestWalletwiseApplication {

	public static void main(String[] args) {
		SpringApplication.from(WalletwiseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
