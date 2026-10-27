package com.example.fitmapp.data

data class ElectiveGroup(val nameTh: String, val nameEn: String, val courses: String)

data class LearnInfo(
    val intro: String,
    val groupsTitle: String,
    val groups: List<ElectiveGroup>,
    val outcomes: List<String>
)

object LearnData {

    private val itOutcomes = listOf(
        "อธิบายเกี่ยวกับทฤษฎี แนวคิด และหลักการพื้นฐานด้านเทคโนโลยีสารสนเทศได้",
        "ประยุกต์ใช้ความรู้ด้านการเขียนโปรแกรม ฐานข้อมูล และเครือข่ายคอมพิวเตอร์ได้อย่างถูกต้อง",
        "ประยุกต์ความรู้ด้านเทคโนโลยีสารสนเทศเพื่อพัฒนาซอฟต์แวร์ได้อย่างถูกต้อง",
        "ประยุกต์ใช้เทคโนโลยีใหม่เพื่อพัฒนาและปรับปรุงระบบสารสนเทศให้ตรงตามความต้องการของผู้ใช้",
        "จัดการระบบสารสนเทศให้มีความมั่นคงปลอดภัยและมีประสิทธิภาพได้",
        "ศึกษาค้นคว้าความรู้เทคโนโลยีสารสนเทศใหม่ ๆ ได้ด้วยตนเอง",
        "ทำงานร่วมกับผู้อื่นได้ รับผิดชอบในบทบาทหน้าที่ เป็นผู้นำและผู้ตามที่ดี"
    )

    private val ineGroups = listOf(
        ElectiveGroup("เครือข่ายและโครงสร้างพื้นฐาน", "Network & Infrastructure", "Network Programming · Software Defined Networks · DevOps Engineering"),
        ElectiveGroup("การพัฒนาซอฟต์แวร์", "Software Development", "Front-end Development · UX/UI Design · Full Stack System"),
        ElectiveGroup("วิทยาการข้อมูล", "Data Science", "Data Analytics · Business Intelligence · Big Data Analytics"),
        ElectiveGroup("เทคโนโลยีสมัยใหม่", "Emerging Technology", "Blockchain Technology · Digital Image Processing · Internet of Things")
    )

    private val ineOutcomes = listOf(
        "อธิบายแนวคิดพื้นฐาน หลักการ และเทคนิคของวิศวกรรมสารสนเทศและเครือข่าย",
        "ประยุกต์ใช้ระบบปฏิบัติการ ฐานข้อมูล เซิร์ฟเวอร์ และระบบคลาวด์ในการพัฒนาเทคโนโลยีดิจิทัล",
        "ออกแบบและจัดการระบบเครือข่ายให้มีความมั่นคงปลอดภัยและมีประสิทธิภาพ",
        "ออกแบบและพัฒนาโปรแกรมได้อย่างถูกต้องและตรงตามความต้องการของผู้ใช้",
        "จัดการองค์ประกอบของโครงสร้างพื้นฐานซอฟต์แวร์ให้มีความมั่นคงปลอดภัย",
        "แก้ปัญหาทางวิศวกรรมโดยประยุกต์ใช้เทคโนโลยีปัจจุบันและศึกษาค้นคว้าสิ่งใหม่ด้วยตนเอง",
        "ทำงานร่วมกับผู้อื่น มีความเป็นผู้นำผู้ตามที่ดี สื่อสารอย่างมีประสิทธิภาพ และมีจรรยาบรรณวิชาชีพ"
    )

    val it4 = LearnInfo(
        intro = "วท.บ. เทคโนโลยีสารสนเทศ\nปี 1-2 เรียนพื้นฐานเทคโนโลยีสารสนเทศร่วมกัน ปี 3-4 เลือกเรียนกลุ่มวิชาเฉพาะทาง 1 กลุ่ม (21 หน่วยกิต)",
        groupsTitle = "กลุ่มวิชาเลือกเฉพาะทาง",
        groups = listOf(
            ElectiveGroup("วิทยาการข้อมูล", "Data Science", "Big Data Technology · Artificial Intelligence · Data Engineering"),
            ElectiveGroup("การพัฒนาซอฟต์แวร์", "Software Development", "Advanced Web Programming · Software Testing · Full Stack Software Development"),
            ElectiveGroup("มัลติมีเดียและการพัฒนาเกม", "Multimedia & Game Development", "2D Design and Animation · 3D Modeling and Animation · Game Programming"),
            ElectiveGroup("ธุรกิจดิจิทัล", "Digital Business", "Business Intelligence System · Enterprise Resources Planning · Business Analytics")
        ),
        outcomes = itOutcomes
    )

    val itTransfer = LearnInfo(
        intro = "วท.บ. เทคโนโลยีสารสนเทศ (เทียบโอน)\nเรียน 4 ภาคเรียนต่อยอดจาก ปวส. เน้นธุรกิจดิจิทัล และทำโครงงานเทคโนโลยีสารสนเทศ",
        groupsTitle = "กลุ่มวิชาที่เรียน",
        groups = listOf(
            ElectiveGroup("ข้อมูลและเครือข่าย", "Data & Network", "Database System · Computer Network and Internet · Internet of Things"),
            ElectiveGroup("การพัฒนาซอฟต์แวร์", "Software Development", "Web Application · Mobile Application Development · Software Engineering"),
            ElectiveGroup("ออกแบบและบริหารโครงการ", "Design & Project Management", "User Experience Design · System Analysis and Design · IT Project Management"),
            ElectiveGroup("ธุรกิจดิจิทัล", "Digital Business", "Business Programming · Business Analytics · Enterprise Resources Planning")
        ),
        outcomes = itOutcomes
    )

    val ine4 = LearnInfo(
        intro = "วศ.บ. วิศวกรรมสารสนเทศและเครือข่าย\nปี 1-2 เรียนพื้นฐานวิศวกรรมสารสนเทศและเครือข่ายร่วมกัน ปี 3-4 เลือกเรียนรายวิชาเฉพาะทางตามความสนใจ",
        groupsTitle = "กลุ่มวิชาเลือกเฉพาะทาง",
        groups = ineGroups,
        outcomes = ineOutcomes
    )

    val ineTransfer = LearnInfo(
        intro = "วศ.บ. วิศวกรรมสารสนเทศและเครือข่าย (เทียบโอน)\nเรียน 3 ปีต่อยอดจาก ปวส. ปูพื้นฐานวิศวกรรมสารสนเทศและเครือข่าย แล้วเลือกเรียนรายวิชาเฉพาะทาง 6 หน่วยกิต",
        groupsTitle = "กลุ่มวิชาเลือกเฉพาะทาง",
        groups = ineGroups,
        outcomes = ineOutcomes
    )

    val ind = LearnInfo(
        intro = "อส.บ. เทคโนโลยีสารสนเทศ (ต่อเนื่อง)\nเรียน 2 ปีต่อจาก ปวส. เน้นเทคโนโลยีสารสนเทศสำหรับงานอุตสาหกรรม และเลือกเรียนวิชาเลือก 6 หน่วยกิต",
        groupsTitle = "กลุ่มวิชาเลือกเฉพาะทาง",
        groups = listOf(
            ElectiveGroup("ข้อมูลและปัญญาประดิษฐ์", "Data & AI", "Data Science and Big Data Analytics · Artificial Intelligence · Theory of Computation"),
            ElectiveGroup("ซอฟต์แวร์และการจัดการ", "Software & Management", "Mobile Application Development · Software Engineering · Enterprise Resource Planning"),
            ElectiveGroup("ระบบฝังตัวและสื่อดิจิทัล", "Embedded & Digital Media", "Microcontroller and Embedded System · Digital Image Processing · Information Medium Technology"),
            ElectiveGroup("อุตสาหกรรมอัตโนมัติ", "Industrial Automation", "Industrial Robotics · Programmable Logic Controller · Computer-integrated Manufacturing")
        ),
        outcomes = listOf(
            "มีความรู้ในทฤษฎีและหลักการด้านเทคโนโลยีสารสนเทศและวิทยาการที่เกี่ยวข้อง",
            "วิเคราะห์และออกแบบระบบงานตามกระบวนการพัฒนาด้านเทคโนโลยีสารสนเทศได้",
            "ประยุกต์ใช้เครื่องมือเทคโนโลยีสารสนเทศได้",
            "มีความซื่อสัตย์ รับผิดชอบต่อตนเองและสังคม และมีจรรยาบรรณทางวิชาการและวิชาชีพ",
            "มีทักษะการเรียนรู้ ศึกษาค้นคว้า และคิดริเริ่มแก้ปัญหาและพัฒนางานได้ด้วยตนเอง",
            "ทำงานร่วมกับผู้อื่นได้ สื่อสารได้ดี เป็นผู้พูดและผู้ฟังที่ดี"
        )
    )
}
