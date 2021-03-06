package com.android.vcard;

import cn.com.xy.sms.sdk.db.entity.NumberInfo;
import cn.com.xy.sms.sdk.net.NewXyHttpRunnable;
import cn.com.xy.sms.sdk.ui.popu.popupview.PartViewParam;
import java.util.HashMap;
import java.util.Map;

class JapaneseUtils {
    private static final Map<Character, String> sHalfWidthMap = new HashMap();

    JapaneseUtils() {
    }

    static {
        sHalfWidthMap.put(Character.valueOf('、'), "､");
        sHalfWidthMap.put(Character.valueOf('。'), "｡");
        sHalfWidthMap.put(Character.valueOf('「'), "｢");
        sHalfWidthMap.put(Character.valueOf('」'), "｣");
        sHalfWidthMap.put(Character.valueOf('〜'), "~");
        sHalfWidthMap.put(Character.valueOf('ぁ'), "ｧ");
        sHalfWidthMap.put(Character.valueOf('あ'), "ｱ");
        sHalfWidthMap.put(Character.valueOf('ぃ'), "ｨ");
        sHalfWidthMap.put(Character.valueOf('い'), "ｲ");
        sHalfWidthMap.put(Character.valueOf('ぅ'), "ｩ");
        sHalfWidthMap.put(Character.valueOf('う'), "ｳ");
        sHalfWidthMap.put(Character.valueOf('ぇ'), "ｪ");
        sHalfWidthMap.put(Character.valueOf('え'), "ｴ");
        sHalfWidthMap.put(Character.valueOf('ぉ'), "ｫ");
        sHalfWidthMap.put(Character.valueOf('お'), "ｵ");
        sHalfWidthMap.put(Character.valueOf('か'), "ｶ");
        sHalfWidthMap.put(Character.valueOf('が'), "ｶﾞ");
        sHalfWidthMap.put(Character.valueOf('き'), "ｷ");
        sHalfWidthMap.put(Character.valueOf('ぎ'), "ｷﾞ");
        sHalfWidthMap.put(Character.valueOf('く'), "ｸ");
        sHalfWidthMap.put(Character.valueOf('ぐ'), "ｸﾞ");
        sHalfWidthMap.put(Character.valueOf('け'), "ｹ");
        sHalfWidthMap.put(Character.valueOf('げ'), "ｹﾞ");
        sHalfWidthMap.put(Character.valueOf('こ'), "ｺ");
        sHalfWidthMap.put(Character.valueOf('ご'), "ｺﾞ");
        sHalfWidthMap.put(Character.valueOf('さ'), "ｻ");
        sHalfWidthMap.put(Character.valueOf('ざ'), "ｻﾞ");
        sHalfWidthMap.put(Character.valueOf('し'), "ｼ");
        sHalfWidthMap.put(Character.valueOf('じ'), "ｼﾞ");
        sHalfWidthMap.put(Character.valueOf('す'), "ｽ");
        sHalfWidthMap.put(Character.valueOf('ず'), "ｽﾞ");
        sHalfWidthMap.put(Character.valueOf('せ'), "ｾ");
        sHalfWidthMap.put(Character.valueOf('ぜ'), "ｾﾞ");
        sHalfWidthMap.put(Character.valueOf('そ'), "ｿ");
        sHalfWidthMap.put(Character.valueOf('ぞ'), "ｿﾞ");
        sHalfWidthMap.put(Character.valueOf('た'), "ﾀ");
        sHalfWidthMap.put(Character.valueOf('だ'), "ﾀﾞ");
        sHalfWidthMap.put(Character.valueOf('ち'), "ﾁ");
        sHalfWidthMap.put(Character.valueOf('ぢ'), "ﾁﾞ");
        sHalfWidthMap.put(Character.valueOf('っ'), "ｯ");
        sHalfWidthMap.put(Character.valueOf('つ'), "ﾂ");
        sHalfWidthMap.put(Character.valueOf('づ'), "ﾂﾞ");
        sHalfWidthMap.put(Character.valueOf('て'), "ﾃ");
        sHalfWidthMap.put(Character.valueOf('で'), "ﾃﾞ");
        sHalfWidthMap.put(Character.valueOf('と'), "ﾄ");
        sHalfWidthMap.put(Character.valueOf('ど'), "ﾄﾞ");
        sHalfWidthMap.put(Character.valueOf('な'), "ﾅ");
        sHalfWidthMap.put(Character.valueOf('に'), "ﾆ");
        sHalfWidthMap.put(Character.valueOf('ぬ'), "ﾇ");
        sHalfWidthMap.put(Character.valueOf('ね'), "ﾈ");
        sHalfWidthMap.put(Character.valueOf('の'), "ﾉ");
        sHalfWidthMap.put(Character.valueOf('は'), "ﾊ");
        sHalfWidthMap.put(Character.valueOf('ば'), "ﾊﾞ");
        sHalfWidthMap.put(Character.valueOf('ぱ'), "ﾊﾟ");
        sHalfWidthMap.put(Character.valueOf('ひ'), "ﾋ");
        sHalfWidthMap.put(Character.valueOf('び'), "ﾋﾞ");
        sHalfWidthMap.put(Character.valueOf('ぴ'), "ﾋﾟ");
        sHalfWidthMap.put(Character.valueOf('ふ'), "ﾌ");
        sHalfWidthMap.put(Character.valueOf('ぶ'), "ﾌﾞ");
        sHalfWidthMap.put(Character.valueOf('ぷ'), "ﾌﾟ");
        sHalfWidthMap.put(Character.valueOf('へ'), "ﾍ");
        sHalfWidthMap.put(Character.valueOf('べ'), "ﾍﾞ");
        sHalfWidthMap.put(Character.valueOf('ぺ'), "ﾍﾟ");
        sHalfWidthMap.put(Character.valueOf('ほ'), "ﾎ");
        sHalfWidthMap.put(Character.valueOf('ぼ'), "ﾎﾞ");
        sHalfWidthMap.put(Character.valueOf('ぽ'), "ﾎﾟ");
        sHalfWidthMap.put(Character.valueOf('ま'), "ﾏ");
        sHalfWidthMap.put(Character.valueOf('み'), "ﾐ");
        sHalfWidthMap.put(Character.valueOf('む'), "ﾑ");
        sHalfWidthMap.put(Character.valueOf('め'), "ﾒ");
        sHalfWidthMap.put(Character.valueOf('も'), "ﾓ");
        sHalfWidthMap.put(Character.valueOf('ゃ'), "ｬ");
        sHalfWidthMap.put(Character.valueOf('や'), "ﾔ");
        sHalfWidthMap.put(Character.valueOf('ゅ'), "ｭ");
        sHalfWidthMap.put(Character.valueOf('ゆ'), "ﾕ");
        sHalfWidthMap.put(Character.valueOf('ょ'), "ｮ");
        sHalfWidthMap.put(Character.valueOf('よ'), "ﾖ");
        sHalfWidthMap.put(Character.valueOf('ら'), "ﾗ");
        sHalfWidthMap.put(Character.valueOf('り'), "ﾘ");
        sHalfWidthMap.put(Character.valueOf('る'), "ﾙ");
        sHalfWidthMap.put(Character.valueOf('れ'), "ﾚ");
        sHalfWidthMap.put(Character.valueOf('ろ'), "ﾛ");
        sHalfWidthMap.put(Character.valueOf('ゎ'), "ﾜ");
        sHalfWidthMap.put(Character.valueOf('わ'), "ﾜ");
        sHalfWidthMap.put(Character.valueOf('ゐ'), "ｲ");
        sHalfWidthMap.put(Character.valueOf('ゑ'), "ｴ");
        sHalfWidthMap.put(Character.valueOf('を'), "ｦ");
        sHalfWidthMap.put(Character.valueOf('ん'), "ﾝ");
        sHalfWidthMap.put(Character.valueOf('゛'), "ﾞ");
        sHalfWidthMap.put(Character.valueOf('゜'), "ﾟ");
        sHalfWidthMap.put(Character.valueOf('ァ'), "ｧ");
        sHalfWidthMap.put(Character.valueOf('ア'), "ｱ");
        sHalfWidthMap.put(Character.valueOf('ィ'), "ｨ");
        sHalfWidthMap.put(Character.valueOf('イ'), "ｲ");
        sHalfWidthMap.put(Character.valueOf('ゥ'), "ｩ");
        sHalfWidthMap.put(Character.valueOf('ウ'), "ｳ");
        sHalfWidthMap.put(Character.valueOf('ェ'), "ｪ");
        sHalfWidthMap.put(Character.valueOf('エ'), "ｴ");
        sHalfWidthMap.put(Character.valueOf('ォ'), "ｫ");
        sHalfWidthMap.put(Character.valueOf('オ'), "ｵ");
        sHalfWidthMap.put(Character.valueOf('カ'), "ｶ");
        sHalfWidthMap.put(Character.valueOf('ガ'), "ｶﾞ");
        sHalfWidthMap.put(Character.valueOf('キ'), "ｷ");
        sHalfWidthMap.put(Character.valueOf('ギ'), "ｷﾞ");
        sHalfWidthMap.put(Character.valueOf('ク'), "ｸ");
        sHalfWidthMap.put(Character.valueOf('グ'), "ｸﾞ");
        sHalfWidthMap.put(Character.valueOf('ケ'), "ｹ");
        sHalfWidthMap.put(Character.valueOf('ゲ'), "ｹﾞ");
        sHalfWidthMap.put(Character.valueOf('コ'), "ｺ");
        sHalfWidthMap.put(Character.valueOf('ゴ'), "ｺﾞ");
        sHalfWidthMap.put(Character.valueOf('サ'), "ｻ");
        sHalfWidthMap.put(Character.valueOf('ザ'), "ｻﾞ");
        sHalfWidthMap.put(Character.valueOf('シ'), "ｼ");
        sHalfWidthMap.put(Character.valueOf('ジ'), "ｼﾞ");
        sHalfWidthMap.put(Character.valueOf('ス'), "ｽ");
        sHalfWidthMap.put(Character.valueOf('ズ'), "ｽﾞ");
        sHalfWidthMap.put(Character.valueOf('セ'), "ｾ");
        sHalfWidthMap.put(Character.valueOf('ゼ'), "ｾﾞ");
        sHalfWidthMap.put(Character.valueOf('ソ'), "ｿ");
        sHalfWidthMap.put(Character.valueOf('ゾ'), "ｿﾞ");
        sHalfWidthMap.put(Character.valueOf('タ'), "ﾀ");
        sHalfWidthMap.put(Character.valueOf('ダ'), "ﾀﾞ");
        sHalfWidthMap.put(Character.valueOf('チ'), "ﾁ");
        sHalfWidthMap.put(Character.valueOf('ヂ'), "ﾁﾞ");
        sHalfWidthMap.put(Character.valueOf('ッ'), "ｯ");
        sHalfWidthMap.put(Character.valueOf('ツ'), "ﾂ");
        sHalfWidthMap.put(Character.valueOf('ヅ'), "ﾂﾞ");
        sHalfWidthMap.put(Character.valueOf('テ'), "ﾃ");
        sHalfWidthMap.put(Character.valueOf('デ'), "ﾃﾞ");
        sHalfWidthMap.put(Character.valueOf('ト'), "ﾄ");
        sHalfWidthMap.put(Character.valueOf('ド'), "ﾄﾞ");
        sHalfWidthMap.put(Character.valueOf('ナ'), "ﾅ");
        sHalfWidthMap.put(Character.valueOf('ニ'), "ﾆ");
        sHalfWidthMap.put(Character.valueOf('ヌ'), "ﾇ");
        sHalfWidthMap.put(Character.valueOf('ネ'), "ﾈ");
        sHalfWidthMap.put(Character.valueOf('ノ'), "ﾉ");
        sHalfWidthMap.put(Character.valueOf('ハ'), "ﾊ");
        sHalfWidthMap.put(Character.valueOf('バ'), "ﾊﾞ");
        sHalfWidthMap.put(Character.valueOf('パ'), "ﾊﾟ");
        sHalfWidthMap.put(Character.valueOf('ヒ'), "ﾋ");
        sHalfWidthMap.put(Character.valueOf('ビ'), "ﾋﾞ");
        sHalfWidthMap.put(Character.valueOf('ピ'), "ﾋﾟ");
        sHalfWidthMap.put(Character.valueOf('フ'), "ﾌ");
        sHalfWidthMap.put(Character.valueOf('ブ'), "ﾌﾞ");
        sHalfWidthMap.put(Character.valueOf('プ'), "ﾌﾟ");
        sHalfWidthMap.put(Character.valueOf('ヘ'), "ﾍ");
        sHalfWidthMap.put(Character.valueOf('ベ'), "ﾍﾞ");
        sHalfWidthMap.put(Character.valueOf('ペ'), "ﾍﾟ");
        sHalfWidthMap.put(Character.valueOf('ホ'), "ﾎ");
        sHalfWidthMap.put(Character.valueOf('ボ'), "ﾎﾞ");
        sHalfWidthMap.put(Character.valueOf('ポ'), "ﾎﾟ");
        sHalfWidthMap.put(Character.valueOf('マ'), "ﾏ");
        sHalfWidthMap.put(Character.valueOf('ミ'), "ﾐ");
        sHalfWidthMap.put(Character.valueOf('ム'), "ﾑ");
        sHalfWidthMap.put(Character.valueOf('メ'), "ﾒ");
        sHalfWidthMap.put(Character.valueOf('モ'), "ﾓ");
        sHalfWidthMap.put(Character.valueOf('ャ'), "ｬ");
        sHalfWidthMap.put(Character.valueOf('ヤ'), "ﾔ");
        sHalfWidthMap.put(Character.valueOf('ュ'), "ｭ");
        sHalfWidthMap.put(Character.valueOf('ユ'), "ﾕ");
        sHalfWidthMap.put(Character.valueOf('ョ'), "ｮ");
        sHalfWidthMap.put(Character.valueOf('ヨ'), "ﾖ");
        sHalfWidthMap.put(Character.valueOf('ラ'), "ﾗ");
        sHalfWidthMap.put(Character.valueOf('リ'), "ﾘ");
        sHalfWidthMap.put(Character.valueOf('ル'), "ﾙ");
        sHalfWidthMap.put(Character.valueOf('レ'), "ﾚ");
        sHalfWidthMap.put(Character.valueOf('ロ'), "ﾛ");
        sHalfWidthMap.put(Character.valueOf('ヮ'), "ﾜ");
        sHalfWidthMap.put(Character.valueOf('ワ'), "ﾜ");
        sHalfWidthMap.put(Character.valueOf('ヰ'), "ｲ");
        sHalfWidthMap.put(Character.valueOf('ヱ'), "ｴ");
        sHalfWidthMap.put(Character.valueOf('ヲ'), "ｦ");
        sHalfWidthMap.put(Character.valueOf('ン'), "ﾝ");
        sHalfWidthMap.put(Character.valueOf('ヴ'), "ｳﾞ");
        sHalfWidthMap.put(Character.valueOf('ヵ'), "ｶ");
        sHalfWidthMap.put(Character.valueOf('ヶ'), "ｹ");
        sHalfWidthMap.put(Character.valueOf('・'), "･");
        sHalfWidthMap.put(Character.valueOf('ー'), "ｰ");
        sHalfWidthMap.put(Character.valueOf('！'), "!");
        sHalfWidthMap.put(Character.valueOf('＂'), "\"");
        sHalfWidthMap.put(Character.valueOf('＃'), "#");
        sHalfWidthMap.put(Character.valueOf('＄'), "$");
        sHalfWidthMap.put(Character.valueOf('％'), "%");
        sHalfWidthMap.put(Character.valueOf('＆'), "&");
        sHalfWidthMap.put(Character.valueOf('＇'), "'");
        sHalfWidthMap.put(Character.valueOf('（'), "(");
        sHalfWidthMap.put(Character.valueOf('）'), ")");
        sHalfWidthMap.put(Character.valueOf('＊'), "*");
        sHalfWidthMap.put(Character.valueOf('＋'), "+");
        sHalfWidthMap.put(Character.valueOf('，'), ",");
        sHalfWidthMap.put(Character.valueOf('－'), "-");
        sHalfWidthMap.put(Character.valueOf('．'), ".");
        sHalfWidthMap.put(Character.valueOf('／'), "/");
        sHalfWidthMap.put(Character.valueOf('０'), "0");
        sHalfWidthMap.put(Character.valueOf('１'), "1");
        sHalfWidthMap.put(Character.valueOf('２'), "2");
        sHalfWidthMap.put(Character.valueOf('３'), NewXyHttpRunnable.ERROR_CODE_SERVICE_ERR);
        sHalfWidthMap.put(Character.valueOf('４'), "4");
        sHalfWidthMap.put(Character.valueOf('５'), "5");
        sHalfWidthMap.put(Character.valueOf('６'), "6");
        sHalfWidthMap.put(Character.valueOf('７'), "7");
        sHalfWidthMap.put(Character.valueOf('８'), "8");
        sHalfWidthMap.put(Character.valueOf('９'), "9");
        sHalfWidthMap.put(Character.valueOf('：'), ":");
        sHalfWidthMap.put(Character.valueOf('；'), ";");
        sHalfWidthMap.put(Character.valueOf('＜'), "<");
        sHalfWidthMap.put(Character.valueOf('＝'), "=");
        sHalfWidthMap.put(Character.valueOf('＞'), ">");
        sHalfWidthMap.put(Character.valueOf('？'), "?");
        sHalfWidthMap.put(Character.valueOf('＠'), "@");
        sHalfWidthMap.put(Character.valueOf('Ａ'), "A");
        sHalfWidthMap.put(Character.valueOf('Ｂ'), PartViewParam.BODY);
        sHalfWidthMap.put(Character.valueOf('Ｃ'), "C");
        sHalfWidthMap.put(Character.valueOf('Ｄ'), "D");
        sHalfWidthMap.put(Character.valueOf('Ｅ'), "E");
        sHalfWidthMap.put(Character.valueOf('Ｆ'), PartViewParam.FOOT);
        sHalfWidthMap.put(Character.valueOf('Ｇ'), "G");
        sHalfWidthMap.put(Character.valueOf('Ｈ'), PartViewParam.HEAD);
        sHalfWidthMap.put(Character.valueOf('Ｉ'), "I");
        sHalfWidthMap.put(Character.valueOf('Ｊ'), "J");
        sHalfWidthMap.put(Character.valueOf('Ｋ'), "K");
        sHalfWidthMap.put(Character.valueOf('Ｌ'), "L");
        sHalfWidthMap.put(Character.valueOf('Ｍ'), "M");
        sHalfWidthMap.put(Character.valueOf('Ｎ'), "N");
        sHalfWidthMap.put(Character.valueOf('Ｏ'), "O");
        sHalfWidthMap.put(Character.valueOf('Ｐ'), "P");
        sHalfWidthMap.put(Character.valueOf('Ｑ'), "Q");
        sHalfWidthMap.put(Character.valueOf('Ｒ'), "R");
        sHalfWidthMap.put(Character.valueOf('Ｓ'), "S");
        sHalfWidthMap.put(Character.valueOf('Ｔ'), "T");
        sHalfWidthMap.put(Character.valueOf('Ｕ'), "U");
        sHalfWidthMap.put(Character.valueOf('Ｖ'), "V");
        sHalfWidthMap.put(Character.valueOf('Ｗ'), "W");
        sHalfWidthMap.put(Character.valueOf('Ｘ'), "X");
        sHalfWidthMap.put(Character.valueOf('Ｙ'), "Y");
        sHalfWidthMap.put(Character.valueOf('Ｚ'), "Z");
        sHalfWidthMap.put(Character.valueOf('［'), "[");
        sHalfWidthMap.put(Character.valueOf('＼'), "\\");
        sHalfWidthMap.put(Character.valueOf('］'), "]");
        sHalfWidthMap.put(Character.valueOf('＾'), "^");
        sHalfWidthMap.put(Character.valueOf('＿'), "_");
        sHalfWidthMap.put(Character.valueOf('ａ'), "a");
        sHalfWidthMap.put(Character.valueOf('ｂ'), "b");
        sHalfWidthMap.put(Character.valueOf('ｃ'), "c");
        sHalfWidthMap.put(Character.valueOf('ｄ'), "d");
        sHalfWidthMap.put(Character.valueOf('ｅ'), "e");
        sHalfWidthMap.put(Character.valueOf('ｆ'), "f");
        sHalfWidthMap.put(Character.valueOf('ｇ'), "g");
        sHalfWidthMap.put(Character.valueOf('ｈ'), "h");
        sHalfWidthMap.put(Character.valueOf('ｉ'), "i");
        sHalfWidthMap.put(Character.valueOf('ｊ'), "j");
        sHalfWidthMap.put(Character.valueOf('ｋ'), "k");
        sHalfWidthMap.put(Character.valueOf('ｌ'), "l");
        sHalfWidthMap.put(Character.valueOf('ｍ'), "m");
        sHalfWidthMap.put(Character.valueOf('ｎ'), "n");
        sHalfWidthMap.put(Character.valueOf('ｏ'), "o");
        sHalfWidthMap.put(Character.valueOf('ｐ'), "p");
        sHalfWidthMap.put(Character.valueOf('ｑ'), "q");
        sHalfWidthMap.put(Character.valueOf('ｒ'), "r");
        sHalfWidthMap.put(Character.valueOf('ｓ'), "s");
        sHalfWidthMap.put(Character.valueOf('ｔ'), "t");
        sHalfWidthMap.put(Character.valueOf('ｕ'), NumberInfo.USER_TAG_UPLOAD_STATUS_KEY);
        sHalfWidthMap.put(Character.valueOf('ｖ'), "v");
        sHalfWidthMap.put(Character.valueOf('ｗ'), "w");
        sHalfWidthMap.put(Character.valueOf('ｘ'), "x");
        sHalfWidthMap.put(Character.valueOf('ｙ'), "y");
        sHalfWidthMap.put(Character.valueOf('ｚ'), "z");
        sHalfWidthMap.put(Character.valueOf('｛'), "{");
        sHalfWidthMap.put(Character.valueOf('｜'), "|");
        sHalfWidthMap.put(Character.valueOf('｝'), "}");
        sHalfWidthMap.put(Character.valueOf('～'), "~");
        sHalfWidthMap.put(Character.valueOf('｡'), "｡");
        sHalfWidthMap.put(Character.valueOf('｢'), "｢");
        sHalfWidthMap.put(Character.valueOf('｣'), "｣");
        sHalfWidthMap.put(Character.valueOf('､'), "､");
        sHalfWidthMap.put(Character.valueOf('･'), "･");
        sHalfWidthMap.put(Character.valueOf('ｦ'), "ｦ");
        sHalfWidthMap.put(Character.valueOf('ｧ'), "ｧ");
        sHalfWidthMap.put(Character.valueOf('ｨ'), "ｨ");
        sHalfWidthMap.put(Character.valueOf('ｩ'), "ｩ");
        sHalfWidthMap.put(Character.valueOf('ｪ'), "ｪ");
        sHalfWidthMap.put(Character.valueOf('ｫ'), "ｫ");
        sHalfWidthMap.put(Character.valueOf('ｬ'), "ｬ");
        sHalfWidthMap.put(Character.valueOf('ｭ'), "ｭ");
        sHalfWidthMap.put(Character.valueOf('ｮ'), "ｮ");
        sHalfWidthMap.put(Character.valueOf('ｯ'), "ｯ");
        sHalfWidthMap.put(Character.valueOf('ｰ'), "ｰ");
        sHalfWidthMap.put(Character.valueOf('ｱ'), "ｱ");
        sHalfWidthMap.put(Character.valueOf('ｲ'), "ｲ");
        sHalfWidthMap.put(Character.valueOf('ｳ'), "ｳ");
        sHalfWidthMap.put(Character.valueOf('ｴ'), "ｴ");
        sHalfWidthMap.put(Character.valueOf('ｵ'), "ｵ");
        sHalfWidthMap.put(Character.valueOf('ｶ'), "ｶ");
        sHalfWidthMap.put(Character.valueOf('ｷ'), "ｷ");
        sHalfWidthMap.put(Character.valueOf('ｸ'), "ｸ");
        sHalfWidthMap.put(Character.valueOf('ｹ'), "ｹ");
        sHalfWidthMap.put(Character.valueOf('ｺ'), "ｺ");
        sHalfWidthMap.put(Character.valueOf('ｻ'), "ｻ");
        sHalfWidthMap.put(Character.valueOf('ｼ'), "ｼ");
        sHalfWidthMap.put(Character.valueOf('ｽ'), "ｽ");
        sHalfWidthMap.put(Character.valueOf('ｾ'), "ｾ");
        sHalfWidthMap.put(Character.valueOf('ｿ'), "ｿ");
        sHalfWidthMap.put(Character.valueOf('ﾀ'), "ﾀ");
        sHalfWidthMap.put(Character.valueOf('ﾁ'), "ﾁ");
        sHalfWidthMap.put(Character.valueOf('ﾂ'), "ﾂ");
        sHalfWidthMap.put(Character.valueOf('ﾃ'), "ﾃ");
        sHalfWidthMap.put(Character.valueOf('ﾄ'), "ﾄ");
        sHalfWidthMap.put(Character.valueOf('ﾅ'), "ﾅ");
        sHalfWidthMap.put(Character.valueOf('ﾆ'), "ﾆ");
        sHalfWidthMap.put(Character.valueOf('ﾇ'), "ﾇ");
        sHalfWidthMap.put(Character.valueOf('ﾈ'), "ﾈ");
        sHalfWidthMap.put(Character.valueOf('ﾉ'), "ﾉ");
        sHalfWidthMap.put(Character.valueOf('ﾊ'), "ﾊ");
        sHalfWidthMap.put(Character.valueOf('ﾋ'), "ﾋ");
        sHalfWidthMap.put(Character.valueOf('ﾌ'), "ﾌ");
        sHalfWidthMap.put(Character.valueOf('ﾍ'), "ﾍ");
        sHalfWidthMap.put(Character.valueOf('ﾎ'), "ﾎ");
        sHalfWidthMap.put(Character.valueOf('ﾏ'), "ﾏ");
        sHalfWidthMap.put(Character.valueOf('ﾐ'), "ﾐ");
        sHalfWidthMap.put(Character.valueOf('ﾑ'), "ﾑ");
        sHalfWidthMap.put(Character.valueOf('ﾒ'), "ﾒ");
        sHalfWidthMap.put(Character.valueOf('ﾓ'), "ﾓ");
        sHalfWidthMap.put(Character.valueOf('ﾔ'), "ﾔ");
        sHalfWidthMap.put(Character.valueOf('ﾕ'), "ﾕ");
        sHalfWidthMap.put(Character.valueOf('ﾖ'), "ﾖ");
        sHalfWidthMap.put(Character.valueOf('ﾗ'), "ﾗ");
        sHalfWidthMap.put(Character.valueOf('ﾘ'), "ﾘ");
        sHalfWidthMap.put(Character.valueOf('ﾙ'), "ﾙ");
        sHalfWidthMap.put(Character.valueOf('ﾚ'), "ﾚ");
        sHalfWidthMap.put(Character.valueOf('ﾛ'), "ﾛ");
        sHalfWidthMap.put(Character.valueOf('ﾜ'), "ﾜ");
        sHalfWidthMap.put(Character.valueOf('ﾝ'), "ﾝ");
        sHalfWidthMap.put(Character.valueOf('ﾞ'), "ﾞ");
        sHalfWidthMap.put(Character.valueOf('ﾟ'), "ﾟ");
        sHalfWidthMap.put(Character.valueOf('￥'), "\\");
    }

    public static String tryGetHalfWidthText(char ch) {
        if (sHalfWidthMap.containsKey(Character.valueOf(ch))) {
            return (String) sHalfWidthMap.get(Character.valueOf(ch));
        }
        return null;
    }
}
