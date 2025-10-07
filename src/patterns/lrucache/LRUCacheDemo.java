package patterns.lrucache;

public class LRUCacheDemo {
    static class Image{
        public String fileId;
        public String url;

        public Image(String fileId, String url){
            this.fileId = fileId;
            this.url = url;
        }
    }

    public static void Demo(){

        Image image1 = new Image("121","xyz.com");
        Image image2 = new Image("122","xyz.com");
        Image image3 = new Image("123","xyz.com");
        Image image4 = new Image("124","xyz.com");
        Image image5 = new Image("125","xyz.com");
        Image image6 = new Image("126","xyz.com");
        Image image7 = new Image("127","xyz.com");
        Image image8 = new Image("128","xyz.com");
        Image image9 = new Image("129","xyz.com");

        LRUCache<String,String> cache = new LRUCache<String,String>(5);

        cache.put(image1.fileId,image1.url);
        cache.put(image2.fileId,image2.url);
        cache.put(image3.fileId,image3.url);
        cache.put(image4.fileId,image4.url);
        cache.get(image1.fileId);
        cache.put(image5.fileId,image5.url);
        cache.put(image6.fileId,image6.url);
        cache.put(image7.fileId,image7.url);
        cache.put(image8.fileId,image8.url);
        cache.get(image1.fileId);
        cache.put(image9.fileId,image9.url);


        String url9 = cache.get(image9.fileId);
        String url111 = cache.get(image1.fileId);

        System.out.println("URL for image 9: "+url9);
        System.out.println("URL for image 1: "+( url111==null ? "Not Found!" : url111));

    }
}
