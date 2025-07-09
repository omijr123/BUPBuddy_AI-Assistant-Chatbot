package com.example.bupbuddy;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class KeywordResponseHelper {

    private static Map<String, Integer> getKeywordResponseMap(String language) {
        Map<String, Integer> responses = new HashMap<>();
        if ("en".equals(language)) {
            responses.put("login|admit card", R.string.response_login_admit_card);
            responses.put("admission test result", R.string.response_admission_test_result);
            responses.put("suggestion|tips", R.string.response_suggestion);
            responses.put("question bank", R.string.response_question_bank);
            responses.put("about bup", R.string.response_about_bup);
            responses.put("regulatory bodies", R.string.response_regulatory_bodies);
            responses.put("administration", R.string.response_administration);
            responses.put("academic programs", R.string.response_academic_programs);
            responses.put("grading system", R.string.response_grading_system);
            responses.put("admission|eligibility|cutoff", R.string.response_admission_process);
            responses.put("library", R.string.response_library);
            responses.put("hall|accommodation", R.string.response_hall);
            responses.put("sports|fitness", R.string.response_sports);
            responses.put("cafeteria|food", R.string.response_cafeteria);
            responses.put("auditorium|bijoy|shadhinota", R.string.response_auditorium);
            responses.put("medical|health|doctor", R.string.response_medical);
            responses.put("transport|bus", R.string.response_transport);
            responses.put("ict|services|erp", R.string.response_ict);
            responses.put("safety|support|security", R.string.response_safety);
            responses.put("faculties|departments", R.string.response_faculties);
            responses.put("modern languages|cml", R.string.response_cml);
            responses.put("counseling|cpc", R.string.response_cpc);
            responses.put("higher studies|chsr", R.string.response_chsr);
            responses.put("research center brc", R.string.response_brc);
            responses.put("labs", R.string.response_labs);
            responses.put("clubs", R.string.response_clubs);
            responses.put("rankings", R.string.response_rankings);
            responses.put("alumni", R.string.response_alumni);
            responses.put("vice chancellors", R.string.response_vcs);
            responses.put("affiliated institutes", R.string.response_affiliated);
            responses.put("external links", R.string.response_external_links);
            responses.put("map|location", R.string.response_map);
            responses.put("faq", R.string.response_faq);
            responses.put("virtual tour", R.string.response_virtual_tour);
            responses.put("campus life", R.string.response_campus_life);
            responses.put("international", R.string.response_international);
            responses.put("office", R.string.response_office);
            responses.put("notice", R.string.response_notice);
            responses.put("events", R.string.response_events);
            responses.put("how to apply", R.string.response_how_to_apply);
        } else { // Bengali
            responses.put("লগইন|প্রবেশপত্র", R.string.response_bn_login_admit_card);
            responses.put("ভর্তি পরীক্ষার ফলাফল", R.string.response_bn_admission_test_result);
            responses.put("সাজেশন|টিপস", R.string.response_bn_suggestion);
            responses.put("প্রশ্ন ব্যাংক", R.string.response_bn_question_bank);
            responses.put("সম্পর্কে", R.string.response_bn_about_bup);
            responses.put("নিয়ন্ত্রক সংস্থা", R.string.response_bn_regulatory_bodies);
            responses.put("প্রশাসন", R.string.response_bn_administration);
            responses.put("একাডেমিক প্রোগ্রাম", R.string.response_bn_academic_programs);
            responses.put("গ্রেডিং সিস্টেম", R.string.response_bn_grading_system);
            responses.put("ভর্তি|যোগ্যতা|কাটঅফ", R.string.response_bn_admission_process);
            responses.put("লাইব্রেরি", R.string.response_bn_library);
            responses.put("হল|আবাসন", R.string.response_bn_hall);
            responses.put("খেলাধুলা", R.string.response_bn_sports);
            responses.put("ক্যাফেটেরিয়া", R.string.response_bn_cafeteria);
            responses.put("অডিটোরিয়াম", R.string.response_bn_auditorium);
            responses.put("মেডিকেল", R.string.response_bn_medical);
            responses.put("পরিবহন", R.string.response_bn_transport);
            responses.put("আইসিটি সেবা", R.string.response_bn_ict);
            responses.put("নিরাপত্তা", R.string.response_bn_safety);
            responses.put("অনুষদ", R.string.response_bn_faculties);
            responses.put("আধুনিক ভাষা", R.string.response_bn_cml);
            responses.put("কাউন্সেলিং", R.string.response_bn_cpc);
            responses.put("উচ্চতর গবেষণা", R.string.response_bn_chsr);
            responses.put("বিইউপি গবেষণা কেন্দ্র", R.string.response_bn_brc);
            responses.put("ল্যাব", R.string.response_bn_labs);
            responses.put("ক্লাব", R.string.response_bn_clubs);
            responses.put("র‍্যাঙ্কিং", R.string.response_bn_rankings);
            responses.put("প্রাক্তন", R.string.response_bn_alumni);
            responses.put("উপাচার্য", R.string.response_bn_vcs);
            responses.put("অধিভুক্ত প্রতিষ্ঠান", R.string.response_bn_affiliated);
            responses.put("বহিঃসংযোগ", R.string.response_bn_external_links);
            responses.put("অবস্থান", R.string.response_bn_map);
            responses.put("faq", R.string.response_bn_faq);
            responses.put("ভার্চুয়াল ট্যুর", R.string.response_bn_virtual_tour);
            responses.put("ক্যাম্পাস জীবন", R.string.response_bn_campus_life);
            responses.put("আন্তর্জাতিক", R.string.response_bn_international);
            responses.put("অফিস", R.string.response_bn_office);
            responses.put("নোটিশ", R.string.response_bn_notice);
            responses.put("ইভেন্ট", R.string.response_bn_events);
            responses.put("আবেদন", R.string.response_bn_how_to_apply);
        }
        return responses;
    }

    public static String findKeywordResponse(Context context, String message, String language) {
        Map<String, Integer> responseMap = getKeywordResponseMap(language);
        String lowerCaseMessage = message.toLowerCase();

        for (Map.Entry<String, Integer> entry : responseMap.entrySet()) {
            String[] keywords = entry.getKey().split("\\|");
            for (String keyword : keywords) {
                if (lowerCaseMessage.contains(keyword.trim().toLowerCase())) {
                    return context.getString(entry.getValue());
                }
            }
        }
        return null; // No keyword match found
    }
}