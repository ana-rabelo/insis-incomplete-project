package com.isep.acme.dtos;

import com.isep.acme.services.ImageService;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageDTO {

    private ImageService service;
    private Long id;
    private Long productID;

    public ImageDTO(Long id, Long productID) {
        this.id = id;
        this.productID = productID;
    }

    public Iterable<ImageDTO> getImageProduct() {
        return service.getImageProduct();
    }
}
