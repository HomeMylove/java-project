package com.homemylove.resp;

import com.homemylove.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resp {

    private boolean success  = true;

    private Map<String,Object> data;

    private String msg;

    private int code = 200;

}
