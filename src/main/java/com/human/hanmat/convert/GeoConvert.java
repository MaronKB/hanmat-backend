package com.human.hanmat.convert;

import java.io.*;
import java.sql.Date;
import com.human.hanmat.entity.Restaurant;
import com.human.hanmat.service.RestaurantService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.locationtech.proj4j.BasicCoordinateTransform;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GeoConvert {
    @Autowired
    private RestaurantService restaurantService;

    public void convertGRStoWGS84() throws IOException {
        File file = new File("C:\\Projects\\hanmat-backend\\src\\main\\resources\\static\\restaurant_left.csv");
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file), "EUC-KR"));
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Projects\\hanmat-backend\\src\\main\\resources\\static\\restaurant_left_converted.csv"));
        String[] row;

        List<Restaurant> restaurants = new ArrayList<>();

        try {
            reader.readNext();
            int i = 0;
            int max = 53908;
            do {
                row = reader.readNext();
                i++;
                Restaurant restaurant = new Restaurant();
                // restaurant.setId(1);
                restaurant.setLmmAddr(row[0]);
                if (Objects.equals(row[0], "RESTAURANT_LMM_ADDR")) {
                    continue;
                }
                restaurant.setRoadAddr(row[1]);
                restaurant.setName(row[2]);
                if (row[3].length() < 10) {
                    continue;
                }
                restaurant.setLastModDate(Date.valueOf(row[3].split(" ")[0]));
                if (row[4].length() < 10) {
                    continue;
                }
                restaurant.setX(Double.parseDouble(row[4]));
                if (row[5].length() < 10) {
                    continue;
                }
                restaurant.setY(Double.parseDouble(row[5]));
                restaurant.setRegDate(new Date(System.currentTimeMillis()));
                restaurant.setRegBy("system@hanmat.com");
                restaurant.setLastModBy("system@hanmat.com");
                restaurant.setIsClosed("N");

                ProjCoordinate target = getProjCoordinate(restaurant);

                restaurant.setX(target.x);
                restaurant.setY(target.y);

//                restaurants.add(restaurant);

//                restaurantService.add(restaurant);

                String[] convertedRow = new String[] {
                        restaurant.getName(),
                        restaurant.getLmmAddr(),
                        restaurant.getRoadAddr(),
                        String.valueOf(restaurant.getX()),
                        String.valueOf(restaurant.getY()),
                        restaurant.getRegDate().toString(),
                        restaurant.getRegBy(),
                        restaurant.getLastModDate().toString(),
                        restaurant.getLastModBy(),
                        restaurant.getIsClosed()
                };
                writer.writeNext(convertedRow);
                System.out.println("Conversion progress: " + i + " / " + max);
            } while (row != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } finally {
            reader.close();
            //restaurantService.addMany(restaurants);
            System.out.println("Conversion completed");
        }
    }

    private static ProjCoordinate getProjCoordinate(Restaurant restaurant) {
        double x = restaurant.getX();
        double y = restaurant.getY();

        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem grs80 = crsFactory.createFromName("epsg:2097");
        CoordinateReferenceSystem wgs84 = crsFactory.createFromName("epsg:4326");
        BasicCoordinateTransform transform = new BasicCoordinateTransform(grs80, wgs84);

        ProjCoordinate source = new ProjCoordinate(x, y);
        ProjCoordinate target = new ProjCoordinate();

        transform.transform(source, target);
        return target;
    }

    public void convert() throws IOException {
        convertGRStoWGS84();
    }
}
