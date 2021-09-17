package com.fmi.tourguideassistant.controller;

import com.fmi.tourguideassistant.model.Region;
import com.fmi.tourguideassistant.service.firebase.FirebaseInitializerService;
import com.fmi.tourguideassistant.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController()
public class RegionsController {

    @Autowired
    FirebaseInitializerService db;

    @Autowired
    RegionService regionService;

    @GetMapping("/getRegionById")
    public ResponseEntity<Region> getRegionById(@RequestParam String id) throws ExecutionException, InterruptedException {
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Region region = this.regionService.getRegionById(id);

        if(region == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @GetMapping("/getAllRegions")
    public ResponseEntity<List<Region>> getAllRegions() throws ExecutionException, InterruptedException {
        List<Region> regions = this.regionService.getAllRegions();

        if(regions.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(regions, HttpStatus.OK);
    }
}
