package com.example.project_sample.vo.accommodation;


import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

//@Getter
//@Setter
@Component
@Data
public class AccVo {
    int acc_idx;
    String acc_name;
    String acc_location;
    String acc_service;
    String acc_type;
    String acc_cancel;
    String acc_contact;
    int mem_idx;
    int a_ref;
    int a_step;
    int no;

    List<Acc_PhotoVo> acc_photo_list;

}
