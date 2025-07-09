package com.example.bupbuddy;

import com.example.bupbuddy.models.ActionCategory;
import com.example.bupbuddy.models.ActionItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickActionData {

    public static List<ActionCategory> getQuickActions(String language) {
        List<ActionCategory> categories = new ArrayList<>();
        if ("en".equals(language)) {
            categories.add(new ActionCategory("Academics & Admission", Arrays.asList(
                    new ActionItem("📖", "PROGRAMS", "Academic Programs"),
                    new ActionItem("📊", "ELIGIBILITY", "Eligibility"),
                    new ActionItem("📝", "HOW TO APPLY", "How to apply"),
                    new ActionItem("💡", "SUGGESTIONS", "Suggestions for admission"),
                    new ActionItem("📚", "QUESTION BANKS", "Question Banks"),
                    new ActionItem("📢", "ADMISSION RESULT", "Admission Test Result"),
                    new ActionItem("📋", "ADMISSION PROCESS", "Admission process"),
                    new ActionItem("📝", "GRADING SYSTEM", "Grading system"),
                    new ActionItem("🔑", "LOGIN & ADMIT", "Login and Admit Card"),
                    new ActionItem("❓", "FAQ", "FAQ")
            )));
            categories.add(new ActionCategory("Campus Life", Arrays.asList(
                    new ActionItem("👨‍🎓", "ACCOMMODATION", "Accommodation"),
                    new ActionItem("🕺", "CLUBS", "Clubs"),
                    new ActionItem("🎥", "VIRTUAL TOUR", "Virtual tour"),
                    new ActionItem("🎬", "CAMPUS LIFE", "Campus life"),
                    new ActionItem("⚽", "SPORTS", "Sports"),
                    new ActionItem("🍔", "CAFETERIA", "Cafeteria"),
                    new ActionItem("🎭", "AUDITORIUM", "Auditorium"),
                    new ActionItem("🗓️", "EVENTS", "Events")
            )));
            categories.add(new ActionCategory("Services & Info", Arrays.asList(
                    new ActionItem("📚", "LIBRARY", "Library"),
                    new ActionItem("⚕️", "MEDICAL", "Medical"),
                    new ActionItem("🚗", "TRANSPORT", "Transport"),
                    new ActionItem("🛡️", "SAFETY", "Safety"),
                    new ActionItem("📢", "NOTICES", "Notices"),
                    new ActionItem("🏢", "OFFICES", "Office"),
                    new ActionItem("💻", "ICT SERVICES", "ICT Services"),
                    new ActionItem("🤝", "COUNSELING", "Counseling")
            )));
            categories.add(new ActionCategory("Research & Resources", Arrays.asList(
                    new ActionItem("🔬", "LABS", "Labs"),
                    new ActionItem("🌍", "INTERNATIONAL", "International"),
                    new ActionItem("🌐", "MODERN LANGUAGES", "Modern Languages"),
                    new ActionItem("🎓", "HIGHER STUDIES", "Higher Studies"),
                    new ActionItem("🔍", "BUP RESEARCH CENTER", "BUP Research Centre BRC")
            )));
            categories.add(new ActionCategory("Additional Features", Arrays.asList(
                    new ActionItem("🏛️", "ABOUT BUP", "About BUP"),
                    new ActionItem("📜", "REGULATORY", "Regulatory bodies"),
                    new ActionItem("👨‍💼", "ADMINISTRATION", "Administration"),
                    new ActionItem("🏫", "FACULTIES", "Faculties"),
                    new ActionItem("🏆", "RANKINGS", "Rankings"),
                    new ActionItem("👨‍🏫", "VICE CHANCELLORS", "Vice Chancellors"),
                    new ActionItem("👨‍🎓", "NOTABLE ALUMNI", "Alumni"),
                    new ActionItem("🏫", "AFFILIATED", "Affiliated Institutes"),
                    new ActionItem("🌐", "EXTERNAL LINKS", "External links"),
                    new ActionItem("📍", "FIND US", "Find us on map")
            )));
        } else { // Bengali
            categories.add(new ActionCategory("একাডেমিক ও ভর্তি", Arrays.asList(
                    new ActionItem("📖", "প্রোগ্রাম", "একাডেমিক প্রোগ্রাম"),
                    new ActionItem("📊", "যোগ্যতা", "যোগ্যতা"),
                    new ActionItem("📝", "আবেদন", "আবেদন"),
                    new ActionItem("💡", "সাজেশন", "ভর্তির জন্য সাজেশন"),
                    new ActionItem("📚", "প্রশ্ন ব্যাংক", "প্রশ্ন ব্যাংক"),
                    new ActionItem("📢", "ভর্তি পরীক্ষার ফলাফল", "ভর্তি পরীক্ষার ফলাফল"),
                    new ActionItem("📋", "ভর্তি প্রক্রিয়া", "ভর্তি"),
                    new ActionItem("📝", "গ্রেডিং সিস্টেম", "গ্রেডিং সিস্টেম"),
                    new ActionItem("🔑", "লগইন ও প্রবেশপত্র", "লগইন ও প্রবেশপত্র"),
                    new ActionItem("❓", "FAQ", "FAQ")
            )));
            categories.add(new ActionCategory("ক্যাম্পাস জীবন", Arrays.asList(
                    new ActionItem("👨‍🎓", "আবাসন", "আবাসন"),
                    new ActionItem("🕺", "ক্লাব", "ক্লাব"),
                    new ActionItem("🎥", "ভার্চুয়াল ট্যুর", "ভার্চুয়াল ট্যুর"),
                    new ActionItem("🎬", "ক্যাম্পাস জীবন", "ক্যাম্পাস জীবন"),
                    new ActionItem("⚽", "খেলাধুলা", "খেলাধুলা"),
                    new ActionItem("🍔", "ক্যাফেটেরিয়া", "ক্যাফেটেরিয়া"),
                    new ActionItem("🎭", "অডিটোরিয়াম", "অডিটোরিয়াম"),
                    new ActionItem("🗓️", "ইভেন্ট", "ইভেন্ট")
            )));
            categories.add(new ActionCategory("সেবা ও তথ্য", Arrays.asList(
                    new ActionItem("📚", "লাইব্রেরি", "লাইব্রেরি"),
                    new ActionItem("⚕️", "মেডিকেল", "মেডিকেল"),
                    new ActionItem("🚗", "পরিবহন", "পরিবহন"),
                    new ActionItem("🛡️", "নিরাপত্তা", "নিরাপত্তা"),
                    new ActionItem("📢", "নোটিশ", "নোটিশ"),
                    new ActionItem("🏢", "অফিস", "অফিস"),
                    new ActionItem("💻", "আইসিটি সেবা", "আইসিটি সেবা"),
                    new ActionItem("🤝", "কাউন্সেলিং", "কাউন্সেলিং")
            )));
            categories.add(new ActionCategory("গবেষণা ও রিসোর্স", Arrays.asList(
                    new ActionItem("🔬", "ল্যাব", "ল্যাব"),
                    new ActionItem("🌍", "আন্তর্জাতিক", "আন্তর্জাতিক"),
                    new ActionItem("🌐", "আধুনিক ভাষা", "আধুনিক ভাষা"),
                    new ActionItem("🎓", "উচ্চতর গবেষণা", "উচ্চতর গবেষণা"),
                    new ActionItem("🔍", "বিইউপি গবেষণা কেন্দ্র", "বিইউপি গবেষণা কেন্দ্র")
            )));
            categories.add(new ActionCategory("অতিরিক্ত বৈশিষ্ট্য", Arrays.asList(
                    new ActionItem("🏛️", "BUP সম্পর্কে", "সম্পর্কে"),
                    new ActionItem("📜", "নিয়ন্ত্রক সংস্থা", "নিয়ন্ত্রক সংস্থা"),
                    new ActionItem("👨‍💼", "প্রশাসন", "প্রশাসন"),
                    new ActionItem("🏫", "অনুষদ", "অনুষদ"),
                    new ActionItem("🏆", "র‍্যাঙ্কিং", "র‍্যাঙ্কিং"),
                    new ActionItem("👨‍🏫", "উপাচার্য", "উপাচার্য"),
                    new ActionItem("👨‍🎓", "উল্লেখযোগ্য প্রাক্তন", "প্রাক্তন"),
                    new ActionItem("🏫", "অধিভুক্ত প্রতিষ্ঠান", "অধিভুক্ত প্রতিষ্ঠান"),
                    new ActionItem("🌐", "বহিঃসংযোগ", "বহিঃসংযোগ"),
                    new ActionItem("📍", "আমাদের অবস্থান", "অবস্থান")
            )));
        }
        return categories;
    }
}