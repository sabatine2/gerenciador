package com.login.model;

import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

	@Test
	public void testBuscaAtivo_2()
		throws Exception {
		List<Login> result = Login.buscaAtivo();
	    assertNotNull("", result);
	}

}
