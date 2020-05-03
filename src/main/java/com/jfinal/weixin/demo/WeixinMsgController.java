package com.jfinal.weixin.demo;

import java.util.logging.Logger;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.iot.msg.InEquDataMsg;
import com.jfinal.weixin.iot.msg.InEqubindEvent;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InLinkMsg;
import com.jfinal.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.weixin.sdk.msg.in.InNotDefinedMsg;
import com.jfinal.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.weixin.sdk.msg.in.card.InCardPassCheckEvent;
import com.jfinal.weixin.sdk.msg.in.card.InCardPayOrderEvent;
import com.jfinal.weixin.sdk.msg.in.card.InCardSkuRemindEvent;
import com.jfinal.weixin.sdk.msg.in.card.InMerChantOrderEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUpdateMemberCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserConsumeCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserGetCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserGiftingCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserPayFromCardEvent;
import com.jfinal.weixin.sdk.msg.in.event.InCustomEvent;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMassEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InNotDefinedEvent;
import com.jfinal.weixin.sdk.msg.in.event.InPoiCheckNotifyEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InShakearoundUserShakeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InTemplateMsgEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifyFailEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifySuccessEvent;
import com.jfinal.weixin.sdk.msg.in.event.InWifiEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutImageMsg;
import com.jfinal.weixin.sdk.msg.out.OutMusicMsg;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.jfinal.weixin.sdk.msg.out.OutVoiceMsg;

/**
 * 将此 DemoController 在YourJFinalConfig 中注册路由，
 * 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
 * DemoController 继承自父类 WeixinController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WeixinMsgController extends MsgController {

    private static final String helpStr = "\t发送 help 可获得帮助，发送\"视频\" 可获取视频教程，发送 \"美女\" 可看美女，发送 music 可听音乐 ，发送新闻可看JFinal新版本消息。公众号功能持续完善中";
    
    /**
     * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
     * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
     */
    public ApiConfig getApiConfig() {
        ApiConfig ac = new ApiConfig();
        
        // 配置微信 API 相关常量
        ac.setToken(PropKit.get("token"));
        ac.setAppId(PropKit.get("appId"));
        ac.setAppSecret(PropKit.get("appSecret"));
        
        /**
         *  是否对消息进行加密，对应于微信平台的消息加解密方式：
         *  1：true进行加密且必须配置 encodingAesKey
         *  2：false采用明文模式，同时也支持混合模式
         */
        ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
        ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
        return ac;
    }

//    protected void processInTextMsg(InTextMsg inTextMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inTextMsg);
//        render(outCustomMsg);
//    }
//
//    @Override
//    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inVoiceMsg);
//        render(outCustomMsg);
//    }
//
//    @Override
//    protected void processInVideoMsg(InVideoMsg inVideoMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inVideoMsg);
//        render(outCustomMsg);
//    }
//
//    @Override
//    protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inShortVideoMsg);
//        render(outCustomMsg);
//    }
//
//    @Override
//    protected void processInLocationMsg(InLocationMsg inLocationMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inLocationMsg);
//        render(outCustomMsg);
//    }
//
//    @Override
//    protected void processInLinkMsg(InLinkMsg inLinkMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inLinkMsg);
//        render(outCustomMsg);
//    }
//
//    @Override
//    protected void processInCustomEvent(InCustomEvent inCustomEvent)
//    {
//        logger.debug("测试方法：processInCustomEvent()");
//        renderNull();
//    }
//
//    protected void processInImageMsg(InImageMsg inImageMsg)
//    {
//        //转发给多客服PC客户端
//        OutCustomMsg outCustomMsg = new OutCustomMsg(inImageMsg);
//        render(outCustomMsg);
//    }
//
//    /**
//     * 实现父类抽方法，处理关注/取消关注消息
//     */
//    protected void processInFollowEvent(InFollowEvent inFollowEvent)
//    {
//        if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent()))
//        {
//            logger.debug("关注：" + inFollowEvent.getFromUserName());
//            OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
//            outMsg.setContent("这是Jfinal-weixin测试服务</br>\r\n感谢您的关注");
//            render(outMsg);
//        }
//        // 如果为取消关注事件，将无法接收到传回的信息
//        if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent()))
//        {
//            logger.debug("取消关注：" + inFollowEvent.getFromUserName());
//        }
//    }
//
//    @Override
//    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent)
//    {
//        if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(inQrCodeEvent.getEvent()))
//        {
//            logger.debug("扫码未关注：" + inQrCodeEvent.getFromUserName());
//            OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
//            outMsg.setContent("感谢您的关注，二维码内容：" + inQrCodeEvent.getEventKey());
//            render(outMsg);
//        }
//        if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent()))
//        {
//            logger.debug("扫码已关注：" + inQrCodeEvent.getFromUserName());
//        }
//    }
//
//    @Override
//    protected void processInLocationEvent(InLocationEvent inLocationEvent)
//    {
//        logger.debug("发送地理位置事件：" + inLocationEvent.getFromUserName());
//        OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
//        outMsg.setContent("地理位置是：" + inLocationEvent.getLatitude());
//        render(outMsg);
//    }
//
//    @Override
//    protected void processInMassEvent(InMassEvent inMassEvent)
//    {
//        logger.debug("测试方法：processInMassEvent()");
//        renderNull();
//    }
//
//    /**
//     * 实现父类抽方法，处理自定义菜单事件
//     */
//    protected void processInMenuEvent(InMenuEvent inMenuEvent)
//    {
//        logger.debug("菜单事件：" + inMenuEvent.getFromUserName());
//        OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
//        outMsg.setContent("菜单事件内容是：" + inMenuEvent.getEventKey());
//        render(outMsg);
//    }
//
//    @Override
//    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults)
//    {
//        logger.debug("语音识别事件：" + inSpeechRecognitionResults.getFromUserName());
//        OutTextMsg outMsg = new OutTextMsg(inSpeechRecognitionResults);
//        outMsg.setContent("语音识别内容是：" + inSpeechRecognitionResults.getRecognition());
//        render(outMsg);
//    }
//
//    @Override
//    protected void (InTemplateMsgEvent inTemplateMsgEvent)
//    {
//        logger.debug("测试方法：()");
//        renderNull();
//    }


    /**
     * 实现父类抽方法，处理文本消息
     * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能：
     *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
     *     其它类型的消息会在随后的方法中进行测试
     */
    protected void processInTextMsg(InTextMsg inTextMsg) {
        String msgContent = inTextMsg.getContent().trim();
        // 帮助提示
        if ("help".equalsIgnoreCase(msgContent) || "帮助".equals(msgContent)) {
            OutTextMsg outMsg = new OutTextMsg(inTextMsg);
            outMsg.setContent(helpStr);
            render(outMsg);
        }
        // 图文消息测试
        else if ("news".equalsIgnoreCase(msgContent) || "新闻".equalsIgnoreCase(msgContent)) {
            OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
            outMsg.addNews("我的微信公众号javenlife", "Jfinal开发微信技术交流","https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUosol56OtHjVibWTK9opiaxsYTQHXuRwoib8YobOfqCbykp3ZSaEk8czAqdkAARU0OdKDtv34F5evFIQ/0?wx_fmt=jpeg", "http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=208184833&idx=1&sn=d9e615e45902c3c72db6c24b65c4af3e#rd");
            outMsg.addNews("我的博客《智慧云端日记》", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^", "https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUosol56OtHjVibWTK9opiaxsY9tPDricojmV5xxuLJyibZJXMAdNOx1qbZFcic9SvsPF2fTUnSc9oQB1IQ/0?wx_fmt=jpeg","http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=208413033&idx=1&sn=06e816e1b2905c46c9a81df0ac0b3bad#rd");
            render(outMsg);
        }
        // 音乐消息测试
        else if ("music".equalsIgnoreCase(msgContent) || "音乐".equals(msgContent)) {
            OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
            outMsg.setTitle("When The Stars Go Blue-Venke Knutson");
            outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
            outMsg.setMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
            outMsg.setHqMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
            outMsg.setFuncFlag(true);
            render(outMsg);
        }
        else if ("美女".equalsIgnoreCase(msgContent)) {
            OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
            outMsg.addNews(
                    "javenlife 宝贝更新喽",
                    "javenlife 宝贝更新喽，我们只看美女 ^_^",
                    "https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUosol56OtHjVibWTK9opiaxsYTQHXuRwoib8YobOfqCbykp3ZSaEk8czAqdkAARU0OdKDtv34F5evFIQ/0?wx_fmt=jpeg",
                    "http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=207820985&idx=1&sn=4ef9361e68495fc3ba1d2f7f2bca0511#rd");
          

            render(outMsg);
        }
        // 其它文本消息直接返回原值 + 帮助提示
        else {
            renderOutTextMsg("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
        }
    }

    /**
     * 实现父类抽方法，处理图片消息
     */
    protected void processInImageMsg(InImageMsg inImageMsg) {
        OutImageMsg outMsg = new OutImageMsg(inImageMsg);
        // 将刚发过来的图片再发回去
        outMsg.setMediaId(inImageMsg.getMediaId());
        render(outMsg);
    }

    /**
     * 实现父类抽方法，处理语音消息
     */
    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
        // 将刚发过来的语音再发回去
        outMsg.setMediaId(inVoiceMsg.getMediaId());
        render(outMsg);
    }

    /**
     * 实现父类抽方法，处理视频消息
     */
    protected void processInVideoMsg(InVideoMsg inVideoMsg) {
        /* 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试
        OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
        outMsg.setTitle("OutVideoMsg 发送");
        outMsg.setDescription("刚刚发来的视频再发回去");
        // 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试
        outMsg.setMediaId(inVideoMsg.getMediaId());
        render(outMsg);
        */
        OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
        outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
        render(outMsg);
    }

    @Override
    protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
    {
        OutTextMsg outMsg = new OutTextMsg(inShortVideoMsg);
        outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inShortVideoMsg.getMediaId());
        render(outMsg);
    }

    /**
     * 实现父类抽方法，处理地址位置消息
     */
    protected void processInLocationMsg(InLocationMsg inLocationMsg) {
        OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
        outMsg.setContent("已收到地理位置消息:" +
                            "\nlocation_X = " + inLocationMsg.getLocation_X() +
                            "\nlocation_Y = " + inLocationMsg.getLocation_Y() +
                            "\nscale = " + inLocationMsg.getScale() +
                            "\nlabel = " + inLocationMsg.getLabel());
        render(outMsg);
    }

    /**
     * 实现父类抽方法，处理链接消息
     * 特别注意：测试时需要发送我的收藏中的曾经收藏过的图文消息，直接发送链接地址会当做文本消息来发送
     */
    protected void processInLinkMsg(InLinkMsg inLinkMsg) {
        OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
        outMsg.addNews("链接消息已成功接收", "链接使用图文消息的方式发回给你，还可以使用文本方式发回。点击图文消息可跳转到链接地址页面，是不是很好玩 :)" , "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
        render(outMsg);
    }

    @Override
    protected void processInCustomEvent(InCustomEvent inCustomEvent)
    {
        System.out.println("processInCustomEvent() 方法测试成功");
    }

    /**
     * 实现父类抽方法，处理关注/取消关注消息
     */
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        outMsg.setContent("感谢关注 JFinal Weixin 极速开发服务号，为您节约更多时间，去陪恋人、家人和朋友 :) \n\n\n " + helpStr);
        // 如果为取消关注事件，将无法接收到传回的信息
        render(outMsg);
    }

    /**
     * 实现父类抽方法，处理扫描带参数二维码事件
     */
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
        outMsg.setContent("processInQrCodeEvent() 方法测试成功");
        render(outMsg);
    }

    /**
     * 实现父类抽方法，处理上报地理位置事件
     */
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
        outMsg.setContent("processInLocationEvent() 方法测试成功");
        render(outMsg);
    }

    @Override
    protected void processInMassEvent(InMassEvent inMassEvent)
    {
        System.out.println("processInMassEvent() 方法测试成功");
    }

    /**
     * 实现父类抽方法，处理自定义菜单事件
     */
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        renderOutTextMsg("processInMenuEvent() 方法测试成功");
    }

    /**
     * 实现父类抽方法，处理接收语音识别结果
     */
    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
        renderOutTextMsg("语音识别结果： " + inSpeechRecognitionResults.getRecognition());
    }

    // 处理接收到的模板消息是否送达成功通知事件
    protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
        String status = inTemplateMsgEvent.getStatus();
        renderOutTextMsg("模板消息是否接收成功：" + status);
    }

	@Override
	protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInWifiEvent(InWifiEvent inWifiEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInUserCardEvent(InUserCardEvent inUserCardEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInUpdateMemberCardEvent(InUpdateMemberCardEvent inUpdateMemberCardEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInUserPayFromCardEvent(InUserPayFromCardEvent inUserPayFromCardEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInUserGiftingCardEvent(InUserGiftingCardEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInUserGetCardEvent(InUserGetCardEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInUserConsumeCardEvent(InUserConsumeCardEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInCardSkuRemindEvent(InCardSkuRemindEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInCardPayOrderEvent(InCardPayOrderEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInCardPassCheckEvent(InCardPassCheckEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInEqubindEvent(InEqubindEvent msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInEquDataMsg(InEquDataMsg msg) {
		// TODO Auto-generated method stub
		
	}
}