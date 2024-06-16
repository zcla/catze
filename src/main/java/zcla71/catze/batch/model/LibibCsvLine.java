package zcla71.catze.batch.model;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class LibibCsvLine {
    @CsvBindByName(column = "item_type")
    private String itemType;
    @CsvBindByName
    private String title;
    @CsvBindByName
    private String creators;
    @CsvBindByName(column = "first_name")
    private String firstName;
    @CsvBindByName(column = "last_name")
    private String lastName;
    @CsvBindByName(column = "ean_isbn13")
    private String eanIsbn13;
    @CsvBindByName(column = "upc_isbn10")
    private String upcIsbn10;
    @CsvBindByName
    private String description;
    @CsvBindByName
    private String publisher;
    @CsvBindByName(column = "publish_date")
    private String publishDate;
    @CsvBindByName
    private String group;
    @CsvBindByName
    private String tags;
    @CsvBindByName
    private String notes;
    @CsvBindByName
    private String price;
    @CsvBindByName
    private String length;
    @CsvBindByName(column = "number_of_discs")
    private String numberOfDiscs;
    @CsvBindByName(column = "number_of_players")
    private String numberOfPlayers;
    @CsvBindByName(column = "age_group")
    private String ageGroup;
    @CsvBindByName
    private String ensemble;
    @CsvBindByName(column = "aspect_ratio")
    private String aspectRatio;
    @CsvBindByName
    private String esrb;
    @CsvBindByName
    private String rating;
    @CsvBindByName
    private String review;
    @CsvBindByName(column = "review_date")
    private String reviewDate;
    @CsvBindByName
    private String status;
    @CsvBindByName
    private String began;
    @CsvBindByName
    private String completed;
    @CsvBindByName
    private String added;
    @CsvBindByName
    private String copies;
}
