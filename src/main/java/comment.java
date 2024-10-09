import io.github.jwdeveloper.tiktok.TikTokLive;

public class comment {
    public static void main(String[] args) {
        TikTokLive.newClient("spamsitoh.trs")
                .onComment((liveClient, event) ->
                        System.out.println(  event.getText()  ))
                .buildAndConnect();
    }

}
