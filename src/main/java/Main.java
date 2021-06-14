import express.Express;

import java.util.Map;

public class Main {
  public static void main(String[] args) {
    var app = new Express();
    
    app.get("/", (req, res) -> res.send("<h1>Hello from Java Express!</h1>"));
    app.get("/json", (req, res) -> res.json(Map.of("message", "Greetings from Java Express!")));
    
    int port = 4000; // default port
    
    // loop and set the port value from args
    for(int i = 0; i < args.length; i++) {
      if(args[i].equals("--port")) {
        port = Integer.parseInt(args[i + 1]);
        break;
      }
    }
    
    app.listen(port);
  }
}
