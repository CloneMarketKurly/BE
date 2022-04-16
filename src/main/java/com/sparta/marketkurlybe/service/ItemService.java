package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.repository.ItemRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Builder
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;;

    //상세페이지 조회
    public Map<String, Object> getItemDetails(Long itemId) {
       Item items = itemRepository.findById(itemId).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );

        Map<String, Object> ItemDetailsList = new HashMap<>();
        ItemDetailsList.put("title",items.getTitle());
        ItemDetailsList.put("des",items.getDes());
        ItemDetailsList.put("image",items.getImage());
        ItemDetailsList.put("detail_Image",items.getDetail_Image());
        ItemDetailsList.put("weight",items.getWeight());
        ItemDetailsList.put("delivery",items.getDelivery());
        ItemDetailsList.put("promise",items.getPromise());
        ItemDetailsList.put("price",items.getPrice());

        System.out.println("리스트 : " + ItemDetailsList);

        return ItemDetailsList;

    }


    //크롤링용
//    private static String MarketKurly_URL = "https://www.kurly.com/shop/main/index.php";
//
//    @Transactional
//    @PostConstruct
//    public void getMarketKurlyDatas() throws IOException {
//
//        Document doc = Jsoup.connect(MarketKurly_URL).get();
//
//        //title
//        Elements content = doc.select("div title");
////        String str = content.toString();
////        String title = str.substring(28,40);
//        System.out.println("title " + content);
//
////        //desc
////        Elements description = doc.select("meta[property=og:description]");
////        String str2 = description.toString();
////        String des = str2.substring(41,66);
////        System.out.println("desc " + des);
////
////        //image
////        String image = "https://img-cf.kurly.com/shop/data/goods/1613374213806y0.jpg";
////
////
////        //detail
////        String detail_Image = "https://i.esdrop.com/d/f/zoDvw3Gypq/9hjEyYEeVE.png";
////
////        //중량
////        String weight = "800g";
////
////        //배송구분
////        String delivery = "샛별배송/택배배송";
////
////        //보증 유통기한
////        String promise = "농산물로 별도의 유통기한은 없으나 가급적 빠르게 드시기 바랍니다.";
////
////        //price
////        String price = "7,888";
////
////        Item item = Item.builder()
////                .title(title)
////                .des(des)
////                .image(image)
////                .detail_Image(detail_Image)
////                .weight(weight)
////                .delivery(delivery)
////                .promise(promise)
////                .price(price)
////                .build();
////
////        itemRepository.save(item);
//    }
}
