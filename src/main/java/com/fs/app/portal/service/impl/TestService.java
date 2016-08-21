package com.fs.app.portal.service.impl;

import com.fs.platform.commons.annotation.FeSaComponent;

@FeSaComponent
public class TestService implements ITestService{

	@Override
	public void testone() {
		System.out.println("testtesttest");
	}

}
