import http from "k6/http"

// Define ramp up users
export let options = {
    stages: [
        { duration: "30s", target: 50}
    ]
};

export default function(){
    http.get("http://localhost:8080/cars");
};