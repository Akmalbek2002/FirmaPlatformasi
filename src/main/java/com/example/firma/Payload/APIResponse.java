package com.example.firma.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse {
    private String xabar;
    private boolean holat;
}
