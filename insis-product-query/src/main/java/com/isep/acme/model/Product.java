package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.isep.acme.dtos.ProductDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;

    @NotBlank(message = "SKU is mandatory")
    @Column(unique = true)
    @Size(min = 12, max = 12, message = "SKU must be 12 characters long")
    public String sku;

    @NotBlank(message = "Designation is mandatory")
    @Size(max = 50, message = "Designation must not be greater than 50 characters")
    @Column
    private String designation;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 1200, message = "Description must not be greater than 1200 characters")
    @Column
    private String description;
    
    /*
     * @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
     * private List<Review> review = new ArrayList<Review>();
     */

    public Product(final String sku, final String designation, final String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }

    public void updateProduct(Product p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }
    /*
     * public List<Review> getReview() {
     * return review;
     * }
     * 
     * public void setReview(List<Review> review) {
     * this.review = review;
     * }
     */

}
