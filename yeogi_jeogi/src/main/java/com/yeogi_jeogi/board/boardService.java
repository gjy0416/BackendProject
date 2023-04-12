package com.yeogi_jeogi.board;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class boardService {
	private final boardDAOImpl bDao;
	private final boardAsyncDAOImpl baDao;

	
	public boardDAO boardNService() {
		return bDao;
	}
	@Async
	public Future<boardAsyncDAO> boardAsyncService() {
		return new AsyncResult<boardAsyncDAO>(baDao);
	}
}
