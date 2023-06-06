package com.johan.blignaut.entelect.vitalityincubator.task1;
import com.johan.blignaut.entelect.vitalityincubator.api.HelloWorldApi;
import com.johan.blignaut.entelect.vitalityincubator.api.MessageApi;
import com.johan.blignaut.entelect.vitalityincubator.model.HelloWorld;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController implements HelloWorldApi, MessageApi {

    @Override
    public ResponseEntity<HelloWorld> helloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("Hello World");
        return ResponseEntity.ok(helloWorld);
    }

    @Override
    public ResponseEntity<String> message(String message) {
        return ResponseEntity.ok(message);
    }
}
