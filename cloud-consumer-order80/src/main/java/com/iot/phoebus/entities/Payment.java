package com.iot.phoebus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xinquan.w
 * @date 2021/6/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Payment implements Serializable {

    private Long id;

    private String serial;
}
