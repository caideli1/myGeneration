package com.caideli.springBoot.service.excel;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 订单状态
 * 
 * @author bjy
 * @date 2019/2/26 0026 16:41
 */
public enum OrderStatus {
	// 初始化
	INITIALIZE(-1, "初始化"),
	// 0:失败
	FAILURE(0, "失败"),
	// 1:成功
	SUCCESS(1, "成功"),
	// 2:处理中
	INPROCESSING(2, "处理中"),
	// 3:待处理
	WAITPROCESS(3, "待处理"),


	BANKCARD_VALIDATING(10,"银行卡校验中"),

	WAIT_IN_KUDOS(11, "kudos放款池中等待");

	public int num;

	private String message;

	OrderStatus(int num, String message) {
		this.num = num;
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public static String getMessageByNum(Integer num) {
		Optional<OrderStatus> optional = Stream.of(OrderStatus.values())
				.filter(value -> Objects.equals(num, value.num))
				.findFirst();

		if (optional.isPresent()) {
			return optional.get().getMessage();
		}

		return "未知";
	}
}
