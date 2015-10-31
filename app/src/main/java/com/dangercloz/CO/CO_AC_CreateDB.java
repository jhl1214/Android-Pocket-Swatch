package com.dangercloz.CO;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dangercloz.BO.BO_AC_Main;
import com.dangercloz.pocketswatch.R;

public class CO_AC_CreateDB extends Activity {

    CO_LO_FiberDB fiberDB;
    CO_LO_StoreDB storeDB;
    CO_LO_HashDB hashDB;
    CO_LO_PocketDB pocketDB;
    CO_LO_PocketContainerDB pocketContainerDB;
    CO_LO_WishListDB wishListDB;
    CO_LO_PurchaseHistoryDB purchaseHistoryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_ac_create_db);

        //Button createDB = (Button)findViewById(R.id.btn_create_db);
        Button insertDB = (Button)findViewById(R.id.btn_insert_db);
        Button deleteDB = (Button)findViewById(R.id.btn_delete_db);

        insertDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiberDB = new CO_LO_FiberDB(v.getContext());
                storeDB = new CO_LO_StoreDB(v.getContext());
                hashDB = new CO_LO_HashDB(v.getContext());
                pocketDB = new CO_LO_PocketDB(v.getContext());
                pocketContainerDB = new CO_LO_PocketContainerDB(v.getContext());
                wishListDB = new CO_LO_WishListDB(v.getContext());

                addFiber();
                addStore();
                addHash();
            }
        });

        deleteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiberDB = new CO_LO_FiberDB(v.getContext());
                storeDB = new CO_LO_StoreDB(v.getContext());
                hashDB = new CO_LO_HashDB(v.getContext());
                pocketDB = new CO_LO_PocketDB(v.getContext());
                pocketContainerDB = new CO_LO_PocketContainerDB(v.getContext());
                wishListDB = new CO_LO_WishListDB(v.getContext());
                purchaseHistoryDB = new CO_LO_PurchaseHistoryDB(v.getContext());

                fiberDB.deleteFiberTable(v.getContext());
                storeDB.deleteStoreTable(v.getContext());
                hashDB.deleteHashTable(v.getContext());
                pocketDB.deletePocketTable(v.getContext());
                pocketContainerDB.deletePocketContainerTable(v.getContext());
                wishListDB.deleteWishlistTable(v.getContext());
                purchaseHistoryDB.deleteHistoryTable(v.getContext());

                BO_AC_Main.currentPocket = null;
            }
        });

    }

    public void addFiber() {
        fiberDB.addFiber(new CO_OB_Fiber("십자", "화섬", "C30/W70", "Red", "110x76", 5, "red26", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("민트", "화섬", "CM40/P60", "Orange", "110x76", 15, "orange41", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("마린보이", "화섬", "P20/R80", "Yellow", "127x86", 25, "yellow36", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("부엉이", "화섬", "R100", "Green", "127x86", 35, "green51", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("파스텔", "화섬", "W10/A90", "Turquoise", "127x86", 45, "turquoise46", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("밤하늘별", "나염", "A50/PA50", "Poolblue", "127x86", 5, "poolblue56", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("킹덤", "나염", "PA50/C50", "Blue", "127x86", 15, "blue31", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("목마", "나염", "C60/CM40", "Purple", "127x86", 25, "purple16", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("블랙베리", "나염", "C60/CM40", "Black", "127x86", 35, "black11", 12000));
        fiberDB.addFiber(new CO_OB_Fiber("어디션", "나염", "C60/CM40", "Gray", "127x86", 45, "gray06", 14000));
        fiberDB.addFiber(new CO_OB_Fiber("오성이", "직기", "C60/CM40", "Brown", "127x86", 5, "brown01", 15000));
        fiberDB.addFiber(new CO_OB_Fiber("바람이나좀쐐", "직기", "C60/CM40", "White", "127x86", 15, "white21", 6000));

        fiberDB.addFiber(new CO_OB_Fiber("오빠차", "직기", "C60/CM40", "Red", "127x86", 25, "red27", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("사과잼", "직기", "C30/W70", "Orange", "127x86", 35, "orange42", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("다또띠", "직기", "C30/W70", "Yellow", "127x86", 45, "yellow37", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("캔디미디", "면", "C30/W70", "Green", "127x86", 5, "green52", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("면탐탐", "면", "C30/W70", "Turquoise", "127x86", 15, "turquoise47", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("돌고래", "면", "C30/W70", "Poolblue", "127x86", 25, "poolblue57", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("물방울", "면", "C30/W70", "Blue", "127x86", 35, "blue32", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("크리스마스", "면", "C30/W70", "Purple", "127x86", 45, "purple17", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("이빨쟁이", "다이마루", "C30/W70", "Black", "127x86", 5, "black12", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("예감", "다이마루", "C30/W70", "Gray", "96x64", 15, "gray07", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("뉴욕밤하늘", "다이마루", "C30/W70", "Brown", "96x64", 25, "brown02", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("2차가자", "다이마루", "C30/W70", "White", "96x64", 35, "white22", 8000));

        fiberDB.addFiber(new CO_OB_Fiber("블링블링", "다이마루", "CM40/P60", "Red", "96x64", 45, "red28", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("얼그레이", "니트", "CM40/P60", "Orange", "96x64", 5, "orange43", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("양은솔", "니트", "CM40/P60", "Yellow", "96x64", 15, "yellow38", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("여루", "니트", "CM40/P60", "Green", "96x64", 25, "green53", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("학이", "니트", "CM40/P60", "Turquoise", "96x64", 35, "turquoise48", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("봉송이", "니트", "CM40/P60", "Poolblue", "96x64", 45, "poolblue58", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("서누", "인조피혁", "CM40/P60", "Blue", "96x64", 5, "blue33", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("만죵이", "인조피혁", "CM40/P60", "Purple", "96x64", 15, "purple18", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("딘두", "인조피혁", "CM40/P60", "Black", "96x64", 25, "black13", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("나롱이", "인조피혁", "CM40/P60", "Gray", "96x64", 35, "gray08", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("지니지니", "인조피혁", "CM40/P60", "Brown", "96x64", 45, "brown03", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("갓신우", "누빔", "CM40/P60", "White", "96x64", 5, "white23", 8000));

        fiberDB.addFiber(new CO_OB_Fiber("누노", "누빔", "P20/R80", "Red", "96x64", 15, "red29", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("캐모마일", "누빔", "P20/R80", "Orange", "96x64", 25, "orange44", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("류진", "누빔", "P20/R80", "Yellow", "96x64", 35, "yellow39", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("딘덩", "누빔", "P20/R80", "Green", "96x64", 45, "green54", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("제리", "양복지", "P20/R80", "Turquoise", "96x64", 5, "turquoise49", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("제우스", "양복지", "P20/R80", "Poolblue", "96x64", 15, "poolblue59", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("며니", "양복지", "P20/R80", "Blue", "96x64", 25, "blue34", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("새벽", "양복지", "P20/R80", "Purple", "96x64", 35, "purple19", 10000));
        fiberDB.addFiber(new CO_OB_Fiber("트라이", "양복지", "P20/R80", "Black", "96x64", 45, "black14", 11000));
        fiberDB.addFiber(new CO_OB_Fiber("골드스타", "양장지", "P20/R80", "Gray", "96x64", 5, "gray09", 12000));
        fiberDB.addFiber(new CO_OB_Fiber("마쉬멜로우", "양장지", "P20/R80", "Brown", "96x64", 15, "brown04", 13000));
        fiberDB.addFiber(new CO_OB_Fiber("아이유", "양장지", "P20/R80", "White", "96x64", 25, "white24", 14000));

        fiberDB.addFiber(new CO_OB_Fiber("러버덕", "양장지", "P20/R80", "Red", "96x64", 35, "red30", 15000));
        fiberDB.addFiber(new CO_OB_Fiber("토이스토리", "양장지", "P20/R80", "Orange", "110x76", 45, "orange45", 16000));
        fiberDB.addFiber(new CO_OB_Fiber("치르치르", "광목", "P20/R80", "Yellow", "110x76", 5, "yellow40", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("핫썸머", "광목", "A50/PA50", "Green", "110x76", 15, "green55", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("보통연애", "광목", "A50/PA50", "Turquoise", "110x76", 25, "turquoise50", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("바람이나좀쐐", "광목", "A50/PA50", "Poolblue", "110x76", 35, "poolblue60", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("취향저격", "광목", "A50/PA50", "Blue", "110x76", 45, "blue35", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("갑", "스판", "R100", "Purple", "110x76", 5, "purple20", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("정류장", "스판", "R100", "Black", "110x76", 15, "black15", 10000));
        fiberDB.addFiber(new CO_OB_Fiber("만해", "스판", "R100", "Gray", "110x76", 25, "gray10", 11000));
        fiberDB.addFiber(new CO_OB_Fiber("위잉위잉", "스판", "R100", "Brown", "110x76", 35, "brown05", 12000));
        fiberDB.addFiber(new CO_OB_Fiber("오빠차", "스판", "R100", "White", "110x76", 45, "white25", 13000));

        fiberDB.addFiber(new CO_OB_Fiber("양화대교", "레이스", "R100", "Red", "96x64", 5, "red01", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("멋진헛간", "레이스", "R100", "Orange", "96x64", 15, "orange05", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("거북선", "레이스", "R100", "Yellow", "96x64", 25, "yellow13", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("스폰서", "레이스", "C30/CM70", "Green", "96x64", 35, "green09", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("맙소사", "레이스", "C30/CM70", "Turquoise", "96x64", 45, "turquoise17", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("서울의달", "실크", "C30/CM70", "Poolblue", "96x64", 5, "poolblue41", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("레옹", "실크", "C30/CM70", "Blue", "96x64", 15, "blue36", 10000));
        fiberDB.addFiber(new CO_OB_Fiber("뱅뱅뱅", "실크", "C30/CM70", "Purple", "96x64", 25, "purple45", 11000));
        fiberDB.addFiber(new CO_OB_Fiber("와리가리", "실크", "C30/CM70", "Black", "96x64", 35, "black37", 12000));
        fiberDB.addFiber(new CO_OB_Fiber("쩔어", "실크", "PA40/W60", "Gray", "96x64", 45, "gray29", 13000));
        fiberDB.addFiber(new CO_OB_Fiber("심쿵해", "울", "PA40/W60", "Brown", "96x64", 5, "brown21", 14000));
        fiberDB.addFiber(new CO_OB_Fiber("맨정신", "울", "PA40/W60", "White", "96x64", 15, "white26", 15000));

        fiberDB.addFiber(new CO_OB_Fiber("우연히봄", "울", "PA40/W60", "Red", "96x64", 25, "red02", 16000));
        fiberDB.addFiber(new CO_OB_Fiber("몸매", "울", "PA40/W60", "Orange", "96x64", 35, "orange06", 15000));
        fiberDB.addFiber(new CO_OB_Fiber("선잠", "울", "PA40/W60", "Yellow", "96x64", 45, "yellow14", 14000));
        fiberDB.addFiber(new CO_OB_Fiber("다올이", "알파카", "PA40/W60", "Green", "96x64", 5, "green10", 13000));
        fiberDB.addFiber(new CO_OB_Fiber("밤골목탐험", "알파카", "P70/R30", "Turquoise", "96x64", 15, "turquoise18", 12000));
        fiberDB.addFiber(new CO_OB_Fiber("김치찌개", "알파카", "P70/R30", "Poolblue", "96x64", 25, "poolblue42", 11000));
        fiberDB.addFiber(new CO_OB_Fiber("만덩이", "알파카", "P70/R30", "Blue", "96x64", 35, "blue37", 10000));
        fiberDB.addFiber(new CO_OB_Fiber("민댕", "알파카", "P70/R30", "Purple", "96x64", 45, "purple46", 9000));
        fiberDB.addFiber(new CO_OB_Fiber("홍이", "인조모피", "P70/R30", "Black", "127x86", 5, "black38", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("청희", "인조모피", "P70/R30", "Gray", "127x86", 15, "gray30", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("참깨", "인조모피", "P70/R30", "Brown", "127x86", 25, "brown22", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("솜사탕", "인조모피", "A50/CM50", "White", "127x86", 35, "white27", 5000));

        fiberDB.addFiber(new CO_OB_Fiber("키스미", "인조모피", "A50/CM50", "Red", "127x86", 45, "red03", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("두리두리", "디지털프린트", "A50/CM50", "Orange", "127x86", 5, "orange07", 3000));
        fiberDB.addFiber(new CO_OB_Fiber("딱좋아", "디지털프린트", "A50/CM50", "Yellow", "127x86", 15, "yellow15", 2000));
        fiberDB.addFiber(new CO_OB_Fiber("양파", "디지털프린트", "A50/CM50", "Green", "127x86", 25, "green11", 1000));
        fiberDB.addFiber(new CO_OB_Fiber("비마음", "디지털프린트", "A50/CM50", "Turquoise", "127x86", 35, "turquoise19", 2000));
        fiberDB.addFiber(new CO_OB_Fiber("백수건달", "디지털프린트", "A50/CM50", "Poolblue", "127x86", 45, "poolblue43", 3000));
        fiberDB.addFiber(new CO_OB_Fiber("배웅", "털", "A50/CM50", "Blue", "127x86", 5, "blue38", 4000));
        fiberDB.addFiber(new CO_OB_Fiber("여우야", "털", "A50/CM50", "Purple", "127x86", 15, "purple47", 5000));
        fiberDB.addFiber(new CO_OB_Fiber("봉인해제", "털", "A50/CM50", "Black", "127x86", 25, "black39", 6000));
        fiberDB.addFiber(new CO_OB_Fiber("청춘연가", "털", "A50/CM50", "Gray", "127x86", 35, "gray31", 7000));
        fiberDB.addFiber(new CO_OB_Fiber("무중력", "털", "A50/CM50", "Brown", "127x86", 45, "brown23", 8000));
        fiberDB.addFiber(new CO_OB_Fiber("삶은여행", "기타", "A50/CM50", "White", "127x86", 5, "white28", 9000));

        fiberDB.addFiber(new CO_OB_Fiber("히어로", "기타", "A50/CM50", "Red", "127x86", 15, "red04", 10000));
        fiberDB.addFiber(new CO_OB_Fiber("아이어맨", "기타", "P60/PA40", "Orange", "127x86", 25, "orange08", 11000));
        fiberDB.addFiber(new CO_OB_Fiber("그러저럭", "기타", "P60/PA40", "Yellow", "127x86", 35, "yellow16", 12000));
        fiberDB.addFiber(new CO_OB_Fiber("살만하지", "기타", "P60/PA40", "Green", "127x86", 45, "green12", 13000));

        // Real Fiber
        fiberDB.addFiber(new CO_OB_Fiber("GLASSES", "화섬", "C20/CM80", "Blue", "127x86", 5, "glass_blue", 5000)); // 101
        fiberDB.addFiber(new CO_OB_Fiber("SMALL DIA", "나염", "P40/W60", "Red", "127x86", 15, "smalldiamond_red", 5000)); // 102
        fiberDB.addFiber(new CO_OB_Fiber("LUPANG", "인조모피", "A50/R50", "Brown", "96x64", 45, "rupang_brown", 8000));   // 103
        fiberDB.addFiber(new CO_OB_Fiber("RIBON", "스판", "R100", "Blue", "127x86", 15, "span_ribon_blue", 6000));    // 104
        fiberDB.addFiber(new CO_OB_Fiber("PEARL", "인조모피", "A50/R50", "Brown", "110x76", 35, "pear_hoppy_brwon", 7000));   // 105
        fiberDB.addFiber(new CO_OB_Fiber("SAMSAM", "인조피혁", "CM40/P60", "Brown", "127x86", 35, "pu_brown", 10000));   // 106

        fiberDB.addFiber(new CO_OB_Fiber("BARAM", "광목", "R20/A80", "Brown", "127x86", 25, "arch_brown", 10000));   // 107
        fiberDB.addFiber(new CO_OB_Fiber("MAPLE", "면", "W40/A60", "Black", "110x76", 15, "maple_dia_black", 5000));   // 108
        fiberDB.addFiber(new CO_OB_Fiber("CHACHA", "면", "P67/R33", "Black", "110x76", 15, "pentagon_blue", 4000)); // 109

        fiberDB.addFiber(new CO_OB_Fiber("3903", "면", "P67/R33", "Blue", "110x76", 15, "a3295_blue", 4000)); // 110
        fiberDB.addFiber(new CO_OB_Fiber("PUCANU", "울", "PA40/W60", "Blue", "96x64", 25, "pucanu_green", 7000)); // 111
        fiberDB.addFiber(new CO_OB_Fiber("MAMA", "면", "P67/R33", "Red", "110x76", 15, "a3368_red", 4000)); // 112
        fiberDB.addFiber(new CO_OB_Fiber("GOLDEN", "기타", "P100", "Brown", "110x76", 35, "goldenbell_gold_brown", 12000)); // 113
        fiberDB.addFiber(new CO_OB_Fiber("SOSO", "나염", "W60/A40", "Blue", "127x86", 25, "maple_prada_blue", 6000)); // 114
        fiberDB.addFiber(new CO_OB_Fiber("NANA", "니트", "P100", "Brown", "127x86", 5, "nana_brown", 6000)); // 115
        fiberDB.addFiber(new CO_OB_Fiber("TREE", "털", "A50/CM50", "White", "127x86", 45, "treecoat_white", 9000)); // 116

        fiberDB.addFiber(new CO_OB_Fiber("MANJONG", "인조피혁", "P30/W70", "Brown", "127x86", 45, "bercley_brown", 9000)); // 117
        fiberDB.addFiber(new CO_OB_Fiber("SUNWOO", "기타", "A30/PA70", "White", "127x86", 45, "ballensia_cream_high", 11000)); // 118
        fiberDB.addFiber(new CO_OB_Fiber("JINJU", "기타", "CM30/W70", "Green", "127x86", 45, "pupency_green", 7000));  // 119

        fiberDB.addFiber(new CO_OB_Fiber("DADAH", "양장지", "PA40/CM60", "Red", "127x86", 45, "lex_red", 7000));  // 120
        fiberDB.addFiber(new CO_OB_Fiber("KWON", "인조피혁", "CM40/P60", "Green", "127x86", 45, "a3320_black", 6000)); // 121
    }

    public void addStore() {
        storeDB.addStore(new CO_OB_Store("태영상회", "A동 101호", "02-1101-5678", "02-1101-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("예농", "A동 102호", "02-1102-5678", "02-1102-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("YN ART", "A동 103호", "02-1103-5678", "02-1103-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("엘레강", "A동 104호", "02-1104-5678", "02-1104-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("가나직물", "A동 105호", "02-1105-5678", "02-1105-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("밀레", "A동 106호", "02-1106-5678", "02-1106-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("리즈", "B동 101호", "02-2101-5678", "02-2101-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("루브르", "B동 102호", "02-2102-5678", "02-2102-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("일진상사", "B동 103호", "02-2103-5678", "02-2103-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("화영직물", "B동 104호", "02-2104-5678", "02-2104-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("라인상사", "B동 105호", "02-2105-5678", "02-2105-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("한울", "B동 106호", "02-2106-5678", "02-2106-5679", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("우진", "C동 101호", "02-3101-5678", "02-3101-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("한섬", "C동 102호", "02-3102-5678", "02-3102-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("남성상회", "C동 103호", "02-3103-5678", "02-3103-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("정우", "C동 104호", "02-3104-5678", "02-3104-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("더블유", "C동 105호", "02-3105-5678", "02-3105-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("라포테", "C동 106호", "02-3106-5678", "02-3106-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("연웅직물", "D동 101호", "02-4101-5678", "02-4101-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("데님코리아", "D동 102호", "02-4102-5678", "02-4102-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("기단", "D동 103호", "02-4103-5678", "02-4103-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("로빈", "D동 104호", "02-4104-5678", "02-4104-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("청솔니트", "D동 105호", "02-4105-5678", "02-4105-5679", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("일진상회", "D동 106호", "02-4106-5678", "02-4106-5679", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("테라", "A동 201호", "02-9070-3903", "02-9070-3902", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("코라", "A동 202호", "02-9171-2902", "02-9172-9083", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("원경상사", "A동 203호", "02-5301-3902", "02-5302-3904", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("완울", "A동 204호", "02-6538-9026", "02-5279-9724", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("스마일", "A동 205호", "02-4658-0907", "02-3387-3689", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("부자섬유", "A동 206호", "02-4792-1234", "02-8078-4765", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("은성섬유", "B동 201호", "02-4734-1123", "02-9856-4872", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("민정상회", "B동 202호", "02-8732-0914", "02-8632-9124", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("진주상회", "B동 203호", "02-7143-0231", "02-7142-1103", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("선우상회", "B동 204호", "02-8732-6954", "02-7542-1193", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("현진리상회", "B동 205호", "02-6678-9134", "02-9857-9986", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("유창", "B동 206호", "02-8652-0175", "02-1634-8912", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("동덕직물", "C동 201호", "02-8874-0155", "02-8874-0156", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("경기직물", "C동 202호", "02-2794-8123", "02-2794-8124", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("동부직물", "C동 203호", "02-3558-9764", "02-3558-9765", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("진홍직물", "C동 204호", "02-4862-1664", "02-5887-6419", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("충남상사", "C동 205호", "02-6801-6111", "02-6801-6112", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("성진라사", "C동 206호", "02-5962-0145", "02-5962-0146", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("만덕직물", "D동 201호", "02-6873-1234", "02-6874-1235", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("거보텍스", "D동 202호", "02-7749-1092", "02-1678-9908", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("종로직물", "D동 203호", "02-7749-1093", "02-1678-9909", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("평화상자", "D동 204호", "02-7749-1094", "02-1678-9910", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("나성", "D동 205호", "02-7749-1095", "02-1678-9911", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("준호상회", "D동 206호", "02-7749-1096", "02-1678-9912", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("진영상회", "A동 301호", "02-7749-1097", "02-1678-9913", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("다현상회", "A동 302호", "02-7749-1098", "02-1678-9914", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("통일사", "A동 303호", "02-7749-1099", "02-1678-9915", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("충남상회", "A동 304호", "02-7749-1100", "02-1678-9916", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("충남면업", "A동 305호", "02-7749-1101", "02-1678-9917", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("조광상회", "A동 306호", "02-7749-1102", "02-1678-9918", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("소은상회", "B동 301호", "02-7749-1103", "02-1678-9919", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("골드텍스타일", "B동 302호", "02-7749-1104", "02-1678-9920", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("수원상회", "B동 303호", "02-7749-1105", "02-1678-9921", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("호야", "B동 304호", "02-7749-1106", "02-1678-9922", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("유림직물", "B동 305호", "02-7749-1107", "02-1678-9923", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("신아직물", "B동 306호", "02-7749-1108", "02-1678-9924", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("넘버원", "C동 301호", "02-9070-3903", "02-9071-3901", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("다빈", "C동 302호", "02-9071-3904", "02-9071-3902", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("스팽글로리", "C동 303호", "02-9072-3905", "02-9071-3903", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("선아레스", "C동 304호", "02-9073-3906", "02-9071-3904", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("세실", "C동 305호", "02-9074-3907", "02-9071-3905", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("비단길", "C동 306호", "02-9074-3908", "02-9071-3906", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("덕원", "D동 301호", "02-9074-3909", "02-9071-3907", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("르포봇", "D동 302호", "02-9074-3910", "02-9071-3908", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("삼원실크", "D동 303호", "02-9074-3911", "02-9071-3909", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("이노상사", "D동 304호", "02-9074-3912", "02-9071-3910", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("한진직물", "D동 305호", "02-9074-3913", "02-9071-3911", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("동남직물", "D동 306호", "02-9074-3914", "02-9071-3912", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("몰텍스", "A동 401호", "02-9074-3915", "02-9071-3913", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("세화상사", "A동 402호", "02-9074-3916", "02-9071-3914", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("동방섬유", "A동 403호", "02-9074-3917", "02-9071-3915", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("조인", "A동 404호", "02-9074-3918", "02-9071-3916", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("열매", "A동 405호", "02-9074-3919", "02-9071-3917", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("은진상회", "A동 406호", "02-9074-3920", "02-9071-3918", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("여너상회", "B동 401호", "02-9074-3921", "02-9071-3919", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("대복", "B동 402호", "02-9074-3922", "02-9071-3920", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("진홍모피", "B동 403호", "02-9074-3923", "02-9071-3921", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("옥스퍼", "B동 404호", "02-9074-3924", "02-9071-3922", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("동서", "B동 405호", "02-9074-3925", "02-9071-3923", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("현대직물", "B동 406호", "02-9074-3926", "02-9071-3924", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("광진", "C동 401호", "02-9074-3927", "02-9071-3925", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("우영컴퍼니", "C동 402호", "02-9074-3928", "02-9071-3926", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("대원직물", "C동 403호", "02-9074-3929", "02-9071-3927", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("성우직물", "C동 404호", "02-9074-3930", "02-9071-3928", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("홍텍스타일", "C동 405호", "02-9074-3931", "02-9071-3929", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("효성직물", "C동 406호", "02-9074-3932", "02-9071-3930", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("혜원", "D동 401호", "02-9074-3933", "02-9071-3931", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("태화직물", "D동 402호", "02-9074-3934", "02-9071-3932", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("한솔", "D동 403호", "02-9074-3935", "02-9071-3933", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("대우TEX", "D동 404호", "02-9074-3936", "02-9071-3934", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("서울직물", "D동 405호", "02-9074-3937", "02-9071-3935", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("한라", "D동 406호", "02-9074-3938", "02-9071-3936", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("남북상사", "D동 407호", "02-9074-3939", "02-9071-3937", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("협성직물", "D동 408호", "02-9074-3940", "02-9071-3938", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("미호사", "D동 409호", "02-9074-3941", "02-9071-3939", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("만능직물", "D동 400호", "02-9074-3942", "02-9071-3940", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("메이플", "A동 107호", "02-9074-1070", "02-9071-1072", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("메이플옆집", "A동 108호", "02-9074-1071", "02-9071-1073", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("스마트", "A동 207호", "02-9074-2070", "02-9071-2071", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("윤텍스", "A동 208호", "02-9074-2080", "02-9071-2081", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("슈마르트", "A동 307호", "02-9074-3070", "02-9071-3071", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("오베르텡", "A동 308호", "02-9074-3080", "02-9071-3082", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("슈트르케", "A동 407호", "02-9074-4070", "02-9071-4072", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("레자", "A동 408호", "02-9074-4080", "02-9071-4082", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("유니유노텍", "B동 107호", "02-9074-2107", "02-9071-2117", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("더원", "B동 108호", "02-9074-2108", "02-9071-2118", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("배고파", "B동 207호", "02-9074-2207", "02-9071-2217", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("쿠쿠다스", "B동 208호", "02-9074-2208", "02-9071-2218", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("동대머리", "B동 307호", "02-9074-2307", "02-9071-2317", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("KBS", "B동 308호", "02-9074-2308", "02-9071-2318", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("종로윤", "B동 407호", "02-9074-2407", "02-9071-2417", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("아돌텍스타일", "B동 408호", "02-9074-2408", "02-9071-2418", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("울버린", "C동 107호", "02-9074-3107", "02-9071-3117", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("13사각스마트", "C동 108호", "02-9074-3108", "02-9071-3118", "8:00~19:00"));

        storeDB.addStore(new CO_OB_Store("더원노래", "C동 207호", "02-9074-3207", "02-9071-3217", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("고주파노래", "C동 208호", "02-9074-3208", "02-9071-3218", "8:00~19:00"));
        storeDB.addStore(new CO_OB_Store("더원PU", "C동 307호", "02-9074-3307", "02-9071-3317", "8:00~19:00"));
    }

    public void addHash() {
        hashDB.addHash(new CO_OB_Hash("NEW", "1"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "1"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "1"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "1"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "1"));
        hashDB.addHash(new CO_OB_Hash("방수천", "1"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "1"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "1"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "1"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "1"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "1"));
        hashDB.addHash(new CO_OB_Hash("HOT", "1"));
        hashDB.addHash(new CO_OB_Hash("BEST", "1"));

        hashDB.addHash(new CO_OB_Hash("NEW", "2"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "2"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "2"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "2"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "2"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "2"));
        hashDB.addHash(new CO_OB_Hash("HOT", "2"));
        hashDB.addHash(new CO_OB_Hash("BEST", "2"));

        hashDB.addHash(new CO_OB_Hash("NEW", "3"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "3"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "3"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "3"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "3"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "3"));
        hashDB.addHash(new CO_OB_Hash("HOT", "3"));
        hashDB.addHash(new CO_OB_Hash("BEST", "3"));

        hashDB.addHash(new CO_OB_Hash("NEW", "4"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "4"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "4"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "4"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "4"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "4"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "4"));
        hashDB.addHash(new CO_OB_Hash("HOT", "4"));
        hashDB.addHash(new CO_OB_Hash("BEST", "4"));

        hashDB.addHash(new CO_OB_Hash("NEW", "5"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "5"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "5"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "5"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "5"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "5"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "5"));
        hashDB.addHash(new CO_OB_Hash("HOT", "5"));
        hashDB.addHash(new CO_OB_Hash("BEST", "5"));

        hashDB.addHash(new CO_OB_Hash("NEW", "6"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "6"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "6"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "6"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "6"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "6"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "6"));
        hashDB.addHash(new CO_OB_Hash("HOT", "6"));
        hashDB.addHash(new CO_OB_Hash("BEST", "6"));

        hashDB.addHash(new CO_OB_Hash("NEW", "7"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "7"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "7"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "7"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "7"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "7"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "7"));
        hashDB.addHash(new CO_OB_Hash("HOT", "7"));
        hashDB.addHash(new CO_OB_Hash("BEST", "7"));

        hashDB.addHash(new CO_OB_Hash("NEW", "8"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "8"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "8"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "8"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "8"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "8"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "8"));
        hashDB.addHash(new CO_OB_Hash("HOT", "8"));
        hashDB.addHash(new CO_OB_Hash("BEST", "8"));

        hashDB.addHash(new CO_OB_Hash("NEW", "9"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "9"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "9"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "9"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "9"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "9"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "9"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "9"));
        hashDB.addHash(new CO_OB_Hash("HOT", "9"));
        hashDB.addHash(new CO_OB_Hash("BEST", "9"));

        hashDB.addHash(new CO_OB_Hash("NEW", "10"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "10"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "10"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "10"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "10"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "10"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "10"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "10"));
        hashDB.addHash(new CO_OB_Hash("HOT", "10"));
        hashDB.addHash(new CO_OB_Hash("BEST", "10"));

        hashDB.addHash(new CO_OB_Hash("NEW", "11"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "11"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "11"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "11"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "11"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "11"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "11"));
        hashDB.addHash(new CO_OB_Hash("HOT", "11"));
        hashDB.addHash(new CO_OB_Hash("BEST", "11"));

        hashDB.addHash(new CO_OB_Hash("NEW", "12"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "12"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "12"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "12"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "12"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "12"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "12"));
        hashDB.addHash(new CO_OB_Hash("HOT", "12"));
        hashDB.addHash(new CO_OB_Hash("BEST", "12"));

        hashDB.addHash(new CO_OB_Hash("NEW", "13"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "13"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "13"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "13"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "13"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "13"));
        hashDB.addHash(new CO_OB_Hash("HOT", "13"));
        hashDB.addHash(new CO_OB_Hash("BEST", "13"));

        hashDB.addHash(new CO_OB_Hash("NEW", "14"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "14"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "14"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "14"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "14"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "14"));
        hashDB.addHash(new CO_OB_Hash("HOT", "14"));
        hashDB.addHash(new CO_OB_Hash("BEST", "14"));

        hashDB.addHash(new CO_OB_Hash("NEW", "15"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "15"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "15"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "15"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "15"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "15"));
        hashDB.addHash(new CO_OB_Hash("HOT", "15"));
        hashDB.addHash(new CO_OB_Hash("BEST", "15"));

        hashDB.addHash(new CO_OB_Hash("NEW", "16"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "16"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "16"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "16"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "16"));
        hashDB.addHash(new CO_OB_Hash("HOT", "16"));
        hashDB.addHash(new CO_OB_Hash("BEST", "16"));

        hashDB.addHash(new CO_OB_Hash("NEW", "17"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "17"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "17"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "17"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "17"));
        hashDB.addHash(new CO_OB_Hash("HOT", "17"));
        hashDB.addHash(new CO_OB_Hash("BEST", "17"));

        hashDB.addHash(new CO_OB_Hash("NEW", "18"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "18"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "18"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "18"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "18"));
        hashDB.addHash(new CO_OB_Hash("HOT", "18"));
        hashDB.addHash(new CO_OB_Hash("BEST", "18"));

        hashDB.addHash(new CO_OB_Hash("NEW", "19"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "19"));
        hashDB.addHash(new CO_OB_Hash("봄원단", "19"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "19"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "19"));
        hashDB.addHash(new CO_OB_Hash("HOT", "19"));

        hashDB.addHash(new CO_OB_Hash("NEW", "20"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "20"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "20"));
        hashDB.addHash(new CO_OB_Hash("방수천", "20"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "20"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "20"));
        hashDB.addHash(new CO_OB_Hash("HOT", "20"));

        hashDB.addHash(new CO_OB_Hash("NEW", "21"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "21"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "21"));
        hashDB.addHash(new CO_OB_Hash("방수천", "21"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "21"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "21"));
        hashDB.addHash(new CO_OB_Hash("HOT", "21"));

        hashDB.addHash(new CO_OB_Hash("NEW", "22"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "22"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "22"));
        hashDB.addHash(new CO_OB_Hash("방수천", "22"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "22"));
        hashDB.addHash(new CO_OB_Hash("유럽풍", "22"));
        hashDB.addHash(new CO_OB_Hash("HOT", "22"));

        hashDB.addHash(new CO_OB_Hash("NEW", "23"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "23"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "23"));
        hashDB.addHash(new CO_OB_Hash("방수천", "23"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "23"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "23"));
        hashDB.addHash(new CO_OB_Hash("HOT", "23"));

        hashDB.addHash(new CO_OB_Hash("NEW", "24"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "24"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "24"));
        hashDB.addHash(new CO_OB_Hash("방수천", "24"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "24"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "24"));
        hashDB.addHash(new CO_OB_Hash("HOT", "24"));

        hashDB.addHash(new CO_OB_Hash("NEW", "25"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "25"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "25"));
        hashDB.addHash(new CO_OB_Hash("방수천", "25"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "25"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "25"));
        hashDB.addHash(new CO_OB_Hash("HOT", "25"));

        hashDB.addHash(new CO_OB_Hash("NEW", "26"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "26"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "26"));
        hashDB.addHash(new CO_OB_Hash("방수천", "26"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "26"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "26"));
        hashDB.addHash(new CO_OB_Hash("HOT", "26"));

        hashDB.addHash(new CO_OB_Hash("NEW", "27"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "27"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "27"));
        hashDB.addHash(new CO_OB_Hash("방수천", "27"));
        hashDB.addHash(new CO_OB_Hash("인조", "27"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "27"));
        hashDB.addHash(new CO_OB_Hash("HOT", "27"));

        hashDB.addHash(new CO_OB_Hash("NEW", "28"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "28"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "28"));
        hashDB.addHash(new CO_OB_Hash("방수천", "28"));
        hashDB.addHash(new CO_OB_Hash("인조", "28"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "28"));
        hashDB.addHash(new CO_OB_Hash("HOT", "28"));

        hashDB.addHash(new CO_OB_Hash("NEW", "29"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "29"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "29"));
        hashDB.addHash(new CO_OB_Hash("방수천", "29"));
        hashDB.addHash(new CO_OB_Hash("인조", "29"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "29"));
        hashDB.addHash(new CO_OB_Hash("HOT", "29"));

        hashDB.addHash(new CO_OB_Hash("NEW", "30"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "30"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "30"));
        hashDB.addHash(new CO_OB_Hash("방수천", "30"));
        hashDB.addHash(new CO_OB_Hash("인조", "30"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "30"));
        hashDB.addHash(new CO_OB_Hash("HOT", "30"));

        hashDB.addHash(new CO_OB_Hash("NEW", "31"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "31"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "31"));
        hashDB.addHash(new CO_OB_Hash("방수천", "31"));
        hashDB.addHash(new CO_OB_Hash("인조", "31"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "31"));
        hashDB.addHash(new CO_OB_Hash("HOT", "31"));

        hashDB.addHash(new CO_OB_Hash("NEW", "32"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "32"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "32"));
        hashDB.addHash(new CO_OB_Hash("방수천", "32"));
        hashDB.addHash(new CO_OB_Hash("인조", "32"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "32"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "32"));
        hashDB.addHash(new CO_OB_Hash("HOT", "32"));

        hashDB.addHash(new CO_OB_Hash("NEW", "33"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "33"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "33"));
        hashDB.addHash(new CO_OB_Hash("방수천", "33"));
        hashDB.addHash(new CO_OB_Hash("인조", "33"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "33"));
        hashDB.addHash(new CO_OB_Hash("HOT", "33"));

        hashDB.addHash(new CO_OB_Hash("NEW", "34"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "34"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "34"));
        hashDB.addHash(new CO_OB_Hash("방수천", "34"));
        hashDB.addHash(new CO_OB_Hash("인조", "34"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "34"));
        hashDB.addHash(new CO_OB_Hash("HOT", "34"));

        hashDB.addHash(new CO_OB_Hash("NEW", "35"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "35"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "35"));
        hashDB.addHash(new CO_OB_Hash("방수천", "35"));
        hashDB.addHash(new CO_OB_Hash("인조", "35"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "35"));
        hashDB.addHash(new CO_OB_Hash("HOT", "35"));

        hashDB.addHash(new CO_OB_Hash("NEW", "36"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "36"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "36"));
        hashDB.addHash(new CO_OB_Hash("방수천", "36"));
        hashDB.addHash(new CO_OB_Hash("인조", "36"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "36"));
        hashDB.addHash(new CO_OB_Hash("HOT", "36"));

        hashDB.addHash(new CO_OB_Hash("NEW", "37"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "37"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "37"));
        hashDB.addHash(new CO_OB_Hash("인조", "37"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "37"));
        hashDB.addHash(new CO_OB_Hash("HOT", "37"));

        hashDB.addHash(new CO_OB_Hash("NEW", "38"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "38"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "38"));
        hashDB.addHash(new CO_OB_Hash("인조", "38"));
        hashDB.addHash(new CO_OB_Hash("HOT", "38"));

        hashDB.addHash(new CO_OB_Hash("NEW", "39"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "39"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "39"));
        hashDB.addHash(new CO_OB_Hash("인조", "39"));
        hashDB.addHash(new CO_OB_Hash("HOT", "39"));

        hashDB.addHash(new CO_OB_Hash("NEW", "40"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "40"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "40"));
        hashDB.addHash(new CO_OB_Hash("인조", "40"));
        hashDB.addHash(new CO_OB_Hash("HOT", "40"));

        hashDB.addHash(new CO_OB_Hash("NEW", "41"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "41"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "41"));
        hashDB.addHash(new CO_OB_Hash("인조", "41"));
        hashDB.addHash(new CO_OB_Hash("HOT", "41"));

        hashDB.addHash(new CO_OB_Hash("NEW", "42"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "42"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "42"));
        hashDB.addHash(new CO_OB_Hash("인조", "42"));
        hashDB.addHash(new CO_OB_Hash("HOT", "42"));

        hashDB.addHash(new CO_OB_Hash("NEW", "43"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "43"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "43"));
        hashDB.addHash(new CO_OB_Hash("인조", "43"));
        hashDB.addHash(new CO_OB_Hash("HOT", "43"));

        hashDB.addHash(new CO_OB_Hash("NEW", "44"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "44"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "44"));
        hashDB.addHash(new CO_OB_Hash("인조", "44"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "44"));
        hashDB.addHash(new CO_OB_Hash("HOT", "44"));

        hashDB.addHash(new CO_OB_Hash("NEW", "45"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "45"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "45"));
        hashDB.addHash(new CO_OB_Hash("인조", "45"));
        hashDB.addHash(new CO_OB_Hash("HOT", "45"));

        hashDB.addHash(new CO_OB_Hash("NEW", "46"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "46"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "46"));
        hashDB.addHash(new CO_OB_Hash("인조", "46"));
        hashDB.addHash(new CO_OB_Hash("HOT", "46"));

        hashDB.addHash(new CO_OB_Hash("NEW", "47"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "47"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "47"));
        hashDB.addHash(new CO_OB_Hash("인조", "47"));
        hashDB.addHash(new CO_OB_Hash("HOT", "47"));

        hashDB.addHash(new CO_OB_Hash("NEW", "48"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "48"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "48"));
        hashDB.addHash(new CO_OB_Hash("인조", "48"));
        hashDB.addHash(new CO_OB_Hash("HOT", "48"));

        hashDB.addHash(new CO_OB_Hash("NEW", "49"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "49"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "49"));
        hashDB.addHash(new CO_OB_Hash("인조", "49"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "49"));
        hashDB.addHash(new CO_OB_Hash("HOT", "49"));

        hashDB.addHash(new CO_OB_Hash("NEW", "50"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "50"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "50"));
        hashDB.addHash(new CO_OB_Hash("인조", "50"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "50"));
        hashDB.addHash(new CO_OB_Hash("HOT", "50"));

        hashDB.addHash(new CO_OB_Hash("NEW", "51"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "51"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "51"));
        hashDB.addHash(new CO_OB_Hash("인조", "51"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "51"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "51"));
        hashDB.addHash(new CO_OB_Hash("HOT", "51"));

        hashDB.addHash(new CO_OB_Hash("NEW", "52"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "52"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "52"));
        hashDB.addHash(new CO_OB_Hash("인조", "52"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "52"));
        hashDB.addHash(new CO_OB_Hash("HOT", "52"));

        hashDB.addHash(new CO_OB_Hash("NEW", "53"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "53"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "53"));
        hashDB.addHash(new CO_OB_Hash("인조", "53"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "53"));
        hashDB.addHash(new CO_OB_Hash("HOT", "53"));

        hashDB.addHash(new CO_OB_Hash("NEW", "54"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "54"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "54"));
        hashDB.addHash(new CO_OB_Hash("인조", "54"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "54"));
        hashDB.addHash(new CO_OB_Hash("HOT", "54"));

        hashDB.addHash(new CO_OB_Hash("NEW", "55"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "55"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "55"));
        hashDB.addHash(new CO_OB_Hash("인조", "55"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "55"));
        hashDB.addHash(new CO_OB_Hash("HOT", "55"));

        hashDB.addHash(new CO_OB_Hash("NEW", "56"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "56"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "56"));
        hashDB.addHash(new CO_OB_Hash("인조", "56"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "56"));
        hashDB.addHash(new CO_OB_Hash("HOT", "56"));

        hashDB.addHash(new CO_OB_Hash("NEW", "57"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "57"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "57"));
        hashDB.addHash(new CO_OB_Hash("인조", "57"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "57"));
        hashDB.addHash(new CO_OB_Hash("HOT", "57"));

        hashDB.addHash(new CO_OB_Hash("NEW", "58"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "58"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "58"));
        hashDB.addHash(new CO_OB_Hash("인조", "58"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "58"));
        hashDB.addHash(new CO_OB_Hash("HOT", "58"));

        hashDB.addHash(new CO_OB_Hash("NEW", "59"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "59"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "59"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "59"));
        hashDB.addHash(new CO_OB_Hash("인조", "59"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "59"));
        hashDB.addHash(new CO_OB_Hash("HOT", "59"));

        hashDB.addHash(new CO_OB_Hash("NEW", "60"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "60"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "60"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "60"));
        hashDB.addHash(new CO_OB_Hash("인조", "60"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "60"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "60"));
        hashDB.addHash(new CO_OB_Hash("HOT", "60"));

        hashDB.addHash(new CO_OB_Hash("NEW", "61"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "61"));
        hashDB.addHash(new CO_OB_Hash("HOT", "61"));

        hashDB.addHash(new CO_OB_Hash("NEW", "62"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "62"));

        hashDB.addHash(new CO_OB_Hash("NEW", "63"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "63"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "63"));

        hashDB.addHash(new CO_OB_Hash("NEW", "64"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "64"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "64"));

        hashDB.addHash(new CO_OB_Hash("NEW", "65"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "65"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "65"));

        hashDB.addHash(new CO_OB_Hash("NEW", "66"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "66"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "66"));
        hashDB.addHash(new CO_OB_Hash("방수천", "66"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "66"));

        hashDB.addHash(new CO_OB_Hash("NEW", "67"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "67"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "67"));
        hashDB.addHash(new CO_OB_Hash("방수천", "67"));

        hashDB.addHash(new CO_OB_Hash("NEW", "68"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "68"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "68"));
        hashDB.addHash(new CO_OB_Hash("방수천", "68"));

        hashDB.addHash(new CO_OB_Hash("NEW", "69"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "69"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "69"));
        hashDB.addHash(new CO_OB_Hash("방수천", "69"));

        hashDB.addHash(new CO_OB_Hash("NEW", "70"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "70"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "70"));
        hashDB.addHash(new CO_OB_Hash("방수천", "70"));

        hashDB.addHash(new CO_OB_Hash("NEW", "71"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "71"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "71"));
        hashDB.addHash(new CO_OB_Hash("방수천", "71"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "71"));

        hashDB.addHash(new CO_OB_Hash("NEW", "72"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "72"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "72"));
        hashDB.addHash(new CO_OB_Hash("방수천", "72"));

        hashDB.addHash(new CO_OB_Hash("NEW", "73"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "73"));
        hashDB.addHash(new CO_OB_Hash("이중직모기모", "73"));
        hashDB.addHash(new CO_OB_Hash("방수천", "73"));

        hashDB.addHash(new CO_OB_Hash("NEW", "74"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "74"));
        hashDB.addHash(new CO_OB_Hash("방수천", "74"));

        hashDB.addHash(new CO_OB_Hash("NEW", "75"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "75"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "75"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "75"));
        hashDB.addHash(new CO_OB_Hash("방수천", "75"));

        hashDB.addHash(new CO_OB_Hash("NEW", "76"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "76"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "76"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "76"));
        hashDB.addHash(new CO_OB_Hash("방수천", "75"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "76"));

        hashDB.addHash(new CO_OB_Hash("NEW", "77"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "77"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "77"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "77"));
        hashDB.addHash(new CO_OB_Hash("방수천", "77"));

        hashDB.addHash(new CO_OB_Hash("NEW", "78"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "78"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "78"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "78"));
        hashDB.addHash(new CO_OB_Hash("방수천", "78"));

        hashDB.addHash(new CO_OB_Hash("NEW", "79"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "79"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "79"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "79"));
        hashDB.addHash(new CO_OB_Hash("방수천", "79"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "79"));
        hashDB.addHash(new CO_OB_Hash("BEST", "79"));

        hashDB.addHash(new CO_OB_Hash("NEW", "80"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "80"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "80"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "80"));
        hashDB.addHash(new CO_OB_Hash("방수천", "80"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "80"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "80"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "80"));
        hashDB.addHash(new CO_OB_Hash("BEST", "80"));

        hashDB.addHash(new CO_OB_Hash("NEW", "81"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "81"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "81"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "81"));
        hashDB.addHash(new CO_OB_Hash("방수천", "81"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "81"));
        hashDB.addHash(new CO_OB_Hash("BEST", "81"));

        hashDB.addHash(new CO_OB_Hash("NEW", "82"));
        hashDB.addHash(new CO_OB_Hash("환절기원단", "82"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "82"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "82"));
        hashDB.addHash(new CO_OB_Hash("방수천", "82"));
        hashDB.addHash(new CO_OB_Hash("인조", "82"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "81"));
        hashDB.addHash(new CO_OB_Hash("BEST", "81"));

        hashDB.addHash(new CO_OB_Hash("NEW", "83"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "83"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "83"));
        hashDB.addHash(new CO_OB_Hash("방수천", "83"));
        hashDB.addHash(new CO_OB_Hash("인조", "83"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "83"));
        hashDB.addHash(new CO_OB_Hash("BEST", "83"));

        hashDB.addHash(new CO_OB_Hash("NEW", "84"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "84"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "84"));
        hashDB.addHash(new CO_OB_Hash("방수천", "84"));
        hashDB.addHash(new CO_OB_Hash("인조", "84"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "84"));
        hashDB.addHash(new CO_OB_Hash("BEST", "84"));

        hashDB.addHash(new CO_OB_Hash("NEW", "85"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "85"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "85"));
        hashDB.addHash(new CO_OB_Hash("방수천", "85"));
        hashDB.addHash(new CO_OB_Hash("인조", "85"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "85"));
        hashDB.addHash(new CO_OB_Hash("BEST", "85"));

        hashDB.addHash(new CO_OB_Hash("NEW", "86"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "86"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "86"));
        hashDB.addHash(new CO_OB_Hash("인조", "86"));
        hashDB.addHash(new CO_OB_Hash("BEST", "86"));

        hashDB.addHash(new CO_OB_Hash("NEW", "87"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "87"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "87"));
        hashDB.addHash(new CO_OB_Hash("인조", "87"));
        hashDB.addHash(new CO_OB_Hash("BEST", "87"));

        hashDB.addHash(new CO_OB_Hash("NEW", "88"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "88"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "88"));
        hashDB.addHash(new CO_OB_Hash("인조", "88"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "88"));
        hashDB.addHash(new CO_OB_Hash("BEST", "88"));

        hashDB.addHash(new CO_OB_Hash("NEW", "89"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "89"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "89"));
        hashDB.addHash(new CO_OB_Hash("인조", "89"));
        hashDB.addHash(new CO_OB_Hash("HOT", "89"));
        hashDB.addHash(new CO_OB_Hash("BEST", "89"));

        hashDB.addHash(new CO_OB_Hash("NEW", "90"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "90"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "90"));
        hashDB.addHash(new CO_OB_Hash("인조", "90"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "90"));
        hashDB.addHash(new CO_OB_Hash("HOT", "90"));
        hashDB.addHash(new CO_OB_Hash("BEST", "90"));

        hashDB.addHash(new CO_OB_Hash("NEW", "91"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "91"));
        hashDB.addHash(new CO_OB_Hash("울혼방", "91"));
        hashDB.addHash(new CO_OB_Hash("인조", "91"));
        hashDB.addHash(new CO_OB_Hash("HOT", "91"));
        hashDB.addHash(new CO_OB_Hash("BEST", "91"));

        hashDB.addHash(new CO_OB_Hash("NEW", "92"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "92"));
        hashDB.addHash(new CO_OB_Hash("인조", "92"));
        hashDB.addHash(new CO_OB_Hash("HOT", "92"));
        hashDB.addHash(new CO_OB_Hash("BEST", "92"));

        hashDB.addHash(new CO_OB_Hash("NEW", "93"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "93"));
        hashDB.addHash(new CO_OB_Hash("인조", "93"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "93"));
        hashDB.addHash(new CO_OB_Hash("HOT", "92"));
        hashDB.addHash(new CO_OB_Hash("BEST", "92"));

        hashDB.addHash(new CO_OB_Hash("NEW", "94"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "94"));
        hashDB.addHash(new CO_OB_Hash("인조", "94"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "94"));
        hashDB.addHash(new CO_OB_Hash("HOT", "94"));
        hashDB.addHash(new CO_OB_Hash("BEST", "94"));

        hashDB.addHash(new CO_OB_Hash("NEW", "95"));
        hashDB.addHash(new CO_OB_Hash("로맨틱", "95"));
        hashDB.addHash(new CO_OB_Hash("인조", "95"));
        hashDB.addHash(new CO_OB_Hash("HOT", "95"));
        hashDB.addHash(new CO_OB_Hash("BEST", "95"));

        hashDB.addHash(new CO_OB_Hash("NEW", "96"));
        hashDB.addHash(new CO_OB_Hash("인조", "96"));
        hashDB.addHash(new CO_OB_Hash("HOT", "96"));
        hashDB.addHash(new CO_OB_Hash("BEST", "96"));

        hashDB.addHash(new CO_OB_Hash("NEW", "97"));
        hashDB.addHash(new CO_OB_Hash("인조", "97"));
        hashDB.addHash(new CO_OB_Hash("울혼방스판모", "97"));
        hashDB.addHash(new CO_OB_Hash("HOT", "97"));
        hashDB.addHash(new CO_OB_Hash("BEST", "97"));

        hashDB.addHash(new CO_OB_Hash("NEW", "98"));
        hashDB.addHash(new CO_OB_Hash("인조", "98"));
        hashDB.addHash(new CO_OB_Hash("HOT", "98"));

        hashDB.addHash(new CO_OB_Hash("NEW", "99"));
        hashDB.addHash(new CO_OB_Hash("인조", "99"));
        hashDB.addHash(new CO_OB_Hash("HOT", "99"));

        hashDB.addHash(new CO_OB_Hash("NEW", "100"));
        hashDB.addHash(new CO_OB_Hash("인조", "100"));
        hashDB.addHash(new CO_OB_Hash("블라섬", "100"));
        hashDB.addHash(new CO_OB_Hash("마들렌", "100"));
        hashDB.addHash(new CO_OB_Hash("HOT", "100"));
        hashDB.addHash(new CO_OB_Hash("BEST", "100"));

        hashDB.addHash(new CO_OB_Hash("NEW", "101"));
        hashDB.addHash(new CO_OB_Hash("HOT", "101"));
        hashDB.addHash(new CO_OB_Hash("BEST", "101"));

        hashDB.addHash(new CO_OB_Hash("NEW", "102"));
        hashDB.addHash(new CO_OB_Hash("HOT", "102"));
        hashDB.addHash(new CO_OB_Hash("BEST", "102"));

        hashDB.addHash(new CO_OB_Hash("NEW", "103"));
        hashDB.addHash(new CO_OB_Hash("HOT", "103"));
        hashDB.addHash(new CO_OB_Hash("BEST", "103"));

        hashDB.addHash(new CO_OB_Hash("NEW", "104"));
        hashDB.addHash(new CO_OB_Hash("HOT", "104"));
        hashDB.addHash(new CO_OB_Hash("BEST", "104"));

        hashDB.addHash(new CO_OB_Hash("NEW", "105"));
        hashDB.addHash(new CO_OB_Hash("HOT", "105"));
        hashDB.addHash(new CO_OB_Hash("BEST", "105"));

        hashDB.addHash(new CO_OB_Hash("NEW", "106"));
        hashDB.addHash(new CO_OB_Hash("HOT", "106"));
        hashDB.addHash(new CO_OB_Hash("BEST", "106"));

        hashDB.addHash(new CO_OB_Hash("NEW", "107"));
        hashDB.addHash(new CO_OB_Hash("HOT", "107"));
        hashDB.addHash(new CO_OB_Hash("BEST", "107"));

        hashDB.addHash(new CO_OB_Hash("NEW", "108"));
        hashDB.addHash(new CO_OB_Hash("HOT", "108"));
        hashDB.addHash(new CO_OB_Hash("BEST", "108"));

        hashDB.addHash(new CO_OB_Hash("NEW", "109"));
        hashDB.addHash(new CO_OB_Hash("HOT", "109"));
        hashDB.addHash(new CO_OB_Hash("BEST", "109"));

        hashDB.addHash(new CO_OB_Hash("NEW", "110"));
        hashDB.addHash(new CO_OB_Hash("HOT", "110"));
        hashDB.addHash(new CO_OB_Hash("BEST", "110"));

        hashDB.addHash(new CO_OB_Hash("NEW", "111"));
        hashDB.addHash(new CO_OB_Hash("HOT", "111"));
        hashDB.addHash(new CO_OB_Hash("BEST", "111"));

        hashDB.addHash(new CO_OB_Hash("NEW", "112"));
        hashDB.addHash(new CO_OB_Hash("HOT", "112"));
        hashDB.addHash(new CO_OB_Hash("BEST", "112"));

        hashDB.addHash(new CO_OB_Hash("NEW", "113"));
        hashDB.addHash(new CO_OB_Hash("HOT", "113"));
        hashDB.addHash(new CO_OB_Hash("BEST", "113"));

        hashDB.addHash(new CO_OB_Hash("NEW", "114"));
        hashDB.addHash(new CO_OB_Hash("HOT", "114"));
        hashDB.addHash(new CO_OB_Hash("BEST", "114"));

        hashDB.addHash(new CO_OB_Hash("NEW", "115"));
        hashDB.addHash(new CO_OB_Hash("HOT", "115"));
        hashDB.addHash(new CO_OB_Hash("BEST", "115"));

        hashDB.addHash(new CO_OB_Hash("NEW", "116"));
        hashDB.addHash(new CO_OB_Hash("HOT", "116"));
        hashDB.addHash(new CO_OB_Hash("BEST", "116"));

        hashDB.addHash(new CO_OB_Hash("NEW", "117"));
        hashDB.addHash(new CO_OB_Hash("HOT", "117"));
        hashDB.addHash(new CO_OB_Hash("BEST", "117"));

        hashDB.addHash(new CO_OB_Hash("NEW", "118"));
        hashDB.addHash(new CO_OB_Hash("HOT", "118"));
        hashDB.addHash(new CO_OB_Hash("BEST", "118"));

        hashDB.addHash(new CO_OB_Hash("NEW", "119"));
        hashDB.addHash(new CO_OB_Hash("HOT", "119"));
        hashDB.addHash(new CO_OB_Hash("BEST", "119"));

        hashDB.addHash(new CO_OB_Hash("NEW", "120"));
        hashDB.addHash(new CO_OB_Hash("HOT", "120"));
        hashDB.addHash(new CO_OB_Hash("BEST", "120"));

        hashDB.addHash(new CO_OB_Hash("NEW", "121"));
        hashDB.addHash(new CO_OB_Hash("HOT", "121"));
        hashDB.addHash(new CO_OB_Hash("BEST", "121"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_co_ac_create_db, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
