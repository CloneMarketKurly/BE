package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.ComResponseDto;
import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.model.Comment;
import com.sparta.marketkurlybe.model.Help;
import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.repository.CommentRepository;
import com.sparta.marketkurlybe.repository.HelpRepository;
import com.sparta.marketkurlybe.repository.ItemRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Builder
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final HelpRepository helpRepository;


    //메인페이지 조회
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    //상세페이지 조회
    public Map<String, Object> getItemDetails(Long itemId) {

       Item items = itemRepository.findById(itemId).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );

        List<Comment> commentList = commentRepository.findByItem_Id(itemId);
        List<ComResponseDto> ComResponseDto = new ArrayList<>();

        for (Comment comment : commentList){
            ComResponseDto commentDto = new ComResponseDto(comment);
            ComResponseDto.add(commentDto);
        }

        Map<String, Object> ItemDetailsList = new HashMap<>();
        ItemDetailsList.put("title",items.getTitle());
        ItemDetailsList.put("des",items.getDes());
        ItemDetailsList.put("image",items.getImage());
        ItemDetailsList.put("detail_Image",items.getDetail_Image());
        ItemDetailsList.put("weight",items.getWeight());
        ItemDetailsList.put("delivery",items.getPacking());
        ItemDetailsList.put("promise",items.getPromise());
        ItemDetailsList.put("price",items.getPrice());
        ItemDetailsList.put("comments", ComResponseDto);

        System.out.println("리스트 : " + ItemDetailsList);

        return ItemDetailsList;
    }


//    //크롤링용
//    private static String MarketKurly_URL = "https://www.kurly.com/shop/main/index.php";
//
//    @PostConstruct
//    public void getMarketKurlyDatas() throws IOException {
//
//        Document doc = Jsoup.connect(MarketKurly_URL).get();

//        //title
//        Elements content = doc.select("img[class=erwlrj80 css-4jombx ebkj6fl0]");
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
