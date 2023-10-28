package com.example.project_sample.vo.accommodation;


import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RoomVo {
    int room_idx;
    String room_name;
    String room_check_in;
    String room_check_out;
    int room_reserv;
    int room_price;
    int room_people;
    String room_service;
    String room_cancel;
    int acc_idx;
    int room_use;
    List<Room_PhotoVo> room_photo_list;

}
