package com.MovieProject.MovieProject.Views.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MovieProject.MovieProject.Views.Views;
import com.MovieProject.MovieProject.Views.Service.ViewsService;
import com.MovieProject.MovieProject.Views.dto.SaveViewRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/views")
public class ViewsController {
    @Autowired
    private ViewsService viewsService;

    @GetMapping()
    public ResponseEntity<List<Views>> getAllViews() {
        return ResponseEntity.ok().body(viewsService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Views> saveResponseEntity(@RequestBody SaveViewRequest req) throws Exception {
        return ResponseEntity.ok().body(viewsService.saveView(req));
    }
    
    @GetMapping("/topten")
    public ResponseEntity<List<Views>> getTopTen() {
        return ResponseEntity.ok().body(viewsService.getTopTen());
    }
    
    
}
