package com.example.fitmapp.data

import com.example.fitmapp.R

enum class Tone(val color: Int, val tint: Int) {
    IT(R.color.primary, R.color.primary_100),
    INE(R.color.ine, R.color.ine_100),
    IND(R.color.ind, R.color.ind_100)
}

data class Course(
    val code: String,
    val nameTh: String,
    val nameEn: String,
    val credits: Int,
    val hours: String,
    val english: Boolean = false,
    val prerequisite: String = "",
    val descriptionEn: String = "",
    val descriptionTh: String = ""
)

data class Term(val title: String, val courses: List<Course>) {
    val totalCredits: Int get() = courses.sumOf { it.credits }
}

data class StudyPlan(val name: String, val detail: String)

data class Program(
    val id: String,
    val degree: String,
    val plan: String,
    val name: String,
    val degreeFull: String,
    val entry: String,
    val credits: Int,
    val feePerTerm: Int,
    val semesters: Int,
    val tone: Tone,
    val revision: String = "",
    val structure: List<Pair<String, Int>> = emptyList(),
    val studyPlans: List<StudyPlan> = emptyList(),
    val terms: List<Term> = emptyList(),
    val placeholder: Boolean = false
)

object ProgramData {

    const val APPLY_URL = "https://stdadmis2.kmutnb.ac.th/ApplyStart"

    val it67Terms = listOf(
        Term("ปีที่ 1 · ภาคเรียนที่ 1", listOf(
            Course("060243101", "เทคโนโลยีสารสนเทศเบื้องต้น", "Fundamental of Information Technology", 3, "2-2-5", prerequisite = "None", descriptionEn = "Evolution of information technology; components of computer system; data processing systems; hardware and software; overview of operating system; data and data representation; data management; basic concept for programming; database technology; data communication; computer network system and Internet; ethical and social issues in information technology."),
            Course("060243102", "การโปรแกรมคอมพิวเตอร์", "Computer Programming", 3, "2-2-5", prerequisite = "None", descriptionEn = "Structure programming language; statement; data type; variable; operator; conditional structure; iteration structure; function and user-defined function; array; file; practice on computer programming.", descriptionTh = "เรียนพื้นฐานการเขียนโปรแกรมเชิงโครงสร้าง ตั้งแต่ชนิดข้อมูล ตัวแปร ตัวดำเนินการ การตัดสินใจ การวนรอบ ฟังก์ชัน อาร์เรย์ และการจัดการไฟล์ พร้อมฝึกเขียนโปรแกรมจริงในห้องปฏิบัติการ"),
            Course("080103001", "ภาษาอังกฤษ 1", "English I", 3, "3-0-6", prerequisite = "None", descriptionEn = "Listening, speaking, reading and writing skills; communicating in simple and routine tasks; reading short passages; writing sentences."),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป (ชุดวิชากีฬาและนันทนาการ)", "Sport and Recreation Elective Course", 1, "0-2-1"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 1 · ภาคเรียนที่ 2", listOf(
            Course("060243104", "การโปรแกรมเชิงวัตถุ", "Object-oriented Programming", 3, "2-2-5", english = true, prerequisite = "None", descriptionEn = "Object-oriented programming language; principle of object-oriented concept; class; object; operator; function; static and instance function; overloading function; error exception; encapsulation and information hiding; inheritance; polymorphisms; practice on object- oriented programming."),
            Course("060243105", "ความรู้พื้นฐานในการออกแบบกราฟิก", "Fundamental of Graphic Design", 3, "2-2-5", prerequisite = "None", descriptionEn = "The principle of design, graphics design’s meaning by emphasizing the principle of design, aesthetic, color, composition, typography theory, information design, and practice digital graphic design using a computer software application."),
            Course("040203123", "คณิตศาสตร์เต็มหน่วยและการประยุกต์", "Discrete Mathematics and Application", 3, "3-0-6", prerequisite = "None", descriptionEn = "Sets and logic, algorithms, basic number theory, mathematical reasoning, counting and applications, generating function, relations, graphs and applications, trees and applications, boolean algebra and applications, modeling computation."),
            Course("080103002", "ภาษาอังกฤษ 2", "English II", 3, "3-0-6", prerequisite = "080103001", descriptionEn = "Listening, speaking, reading and writing skills; communicating and giving opinions toward familiar topics; reading long passages; writing complex sentences and simple paragraphs."),
            Course("080303701", "กระบวนการคิดเชิงออกแบบ", "Design Thinking", 3, "3-0-6", prerequisite = "None", descriptionEn = "Design thinking for designers to develop products, services and strategies to innovations. human-centered design via following processes; empathy, define, Ideate, prototype and test; team-working and working environment to support creativity and ideas."),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 1", listOf(
            Course("060243106", "โครงสร้างข้อมูลและขั้นตอนวิธี", "Data Structure and Algorithm", 3, "3-0-6", english = true, prerequisite = "060243102", descriptionEn = "Abstract of data type; object-oriented data structure; performance of program processing; processing with abstract data, linked list, stack, queue, tree, hash, heap, processing in binary search tree structure, processing in balanced tree structure; sorting algorithm data, comparison of algorithms for processing; graph data structure, weighted graph and non-weighted graph, directed graph and undirected graph, algorithm in graph processing, shortest path algorithm."),
            Course("060243108", "ระบบฐานข้อมูล", "Database System", 3, "3-0-6", english = true, prerequisite = "None", descriptionEn = "Principle of database system; database management system; database system architecture; database design; structure query language; data integrity; concurrency control; security management; data backup; database recovery; database design and development techniques; emerging trends in database system."),
            Course("060243123", "เครือข่ายคอมพิวเตอร์และอินเทอร์เน็ต", "Computer Network and Internet", 3, "3-0-6", prerequisite = "None", descriptionEn = "Data communication network, data transmission, intermediary, data link path control, local and wide area computer networks, communication architecture; TCP/IP protocols, IP address design, routing protocol, links between networks using TCP/IP, links between applications in network, client - server processing."),
            Course("060243111", "วิศวกรรมซอฟต์แวร์", "Software Engineering", 3, "3-0-6", english = true, prerequisite = "None", descriptionEn = "Principle of software engineering, software requirement specification, software process, software modeling, software cost estimation, software project management, software testing, software quality, manual documentation, software delivery, software maintenance."),
            Course("080203914", "ผู้ประกอบการนวัตกรรม", "Innovative Technopreneurs", 3, "3-0-6", prerequisite = "None", descriptionEn = "Concept of entrepreneurship; business initiatives; development of innovative products, and services; business model development; pitching techniques; management of intellectual property and related laws."),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 2", listOf(
            Course("060243110", "การโปรแกรมภาษาจาวาสคริปต์", "JavaScript Programming", 3, "2-2-5", prerequisite = "060243102", descriptionEn = "Language concepts, variable, data types, operator, selection statement, iteration statement, function, error exception, array, symbol, class, collection, iterator, generator and JSON data structure, practicing on javascript programming."),
            Course("060243112", "การวิเคราะห์และออกแบบระบบ", "System Analysis and Design", 3, "3-0-6", prerequisite = "060243108", descriptionEn = "System analysis and design techniques, procedures and method of system analysis and design, IT executive summary, co-operation, software specification, software development, database development, documentation, deployment, system testing and maintenance."),
            Course("060243122", "เว็บแอปพลิเคชัน", "Web Application", 3, "2-2-5", prerequisite = "060243102", descriptionEn = "Internet-based program development; structure of the language used in web development; creating structure for web page; defining format and style of web page; data management in database via web; data security on the Internet."),
            Course("060243124", "ธุรกิจดิจิทัลเบื้องต้น", "Fundamental of Digital Business", 3, "3-0-6", prerequisite = "None", descriptionEn = "Principle and process of digital business, operation of business information systems, information system for human resource management, production information system, marketing information system, financial information system, accounting information system, logistics information system, system development life cycle, application of information systems for business operation."),
            Course("060243125", "สื่อดิจิทัลเบื้องต้น", "Fundamental of Digital Media", 3, "3-0-6", english = true, prerequisite = "None", descriptionEn = "Definition, type, and characteristic of digital media; digital media production process; principle of content writing to conform with digital media attribute; digital media design and production tools; digital media and society; law and ethics related to digital media."),
            Course("060243126", "วิทยาการข้อมูลเบื้องต้น", "Fundamental of Data Science", 3, "3-0-6", prerequisite = "None", descriptionEn = "Principle of data science; fundamental computer programming languages for data science; data collection and acquisition methods; data preparation; statistical data analysis; model creation; knowledge discovery from data; data visualization; handling big data.")
        )),
        Term("ปีที่ 3 · ภาคเรียนที่ 1", listOf(
            Course("060243115", "การออกแบบจากประสบการณ์ผู้ใช้", "User Experience Design", 3, "2-2-5", english = true, prerequisite = "060243105", descriptionEn = "Principles from user experience design, design elements from user experience, user interface design technique, design process from user experience, basic design from user experience, User experience design based on case study situations, prototyping, the initial stage of designing a tool or system focus on user needs."),
            Course("060243116", "อินเทอร์เน็ตในทุกสรรพสิ่ง", "Internet of Things", 3, "2-2-5", english = true, prerequisite = "None", descriptionEn = "Internet of things concept and standard; network protocol, MQTT protocol, M2M protocol, TCP/IP protocol; microcontroller; sensor; wireless technology; IoT cloud service; database; data processing; case study of Internet of Things."),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, "")
        )),
        Term("ปีที่ 3 · ภาคเรียนที่ 2", listOf(
            Course("060243118", "การพัฒนาโปรแกรมประยุกต์บนอุปกรณ์เคลื่อนที่", "Mobile Application Development", 3, "2-2-5", prerequisite = "060243104", descriptionEn = "Technology for application development; mobile and portable device application development; user interface design; data validation and program verification."),
            Course("060243119", "การบริหารโครงการเทคโนโลยีสารสนเทศ", "Information Technology Project Management", 3, "3-0-6", prerequisite = "None", descriptionEn = "Principles of information technology project management; defining project objective and target, project charter development, planning, project management methodology, resource and time management, key project deliverables, issues management, changes management and risks management during the project, progress monitoring and evaluation of project."),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, "")
        )),
        Term("ปีที่ 3 · ภาคฤดูร้อน (โครงการปกติ)", listOf(
            Course("060243204", "การฝึกงาน", "Training", 1, "240 ชั่วโมง", descriptionEn = "Summer semester, students are required to undertake an internship at least 240 hours in their studied fields at information technology companies; assessment is satisfactory (S) or un-satisfactory (U).")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 1 (โครงการปกติ)", listOf(
            Course("060243120", "ความปลอดภัยระบบสารสนเทศ", "Information System Security", 3, "3-0-6", english = true, prerequisite = "None", descriptionEn = "Security techniques; type of threat; protection; access control; classic and modern encryption techniques; key encryption; message authentication and hash functions; digital signature and authentication; tools and techniques for information system security; computer security audit; risk assessment and management in information system security; computer-related crime act."),
            Course("060243121", "สัมมนาเทคโนโลยีสารสนเทศ", "Information Technology Seminar", 1, "0-3-1", prerequisite = "None", descriptionEn = "Seminar principle; seminar project writing, conducting seminars, evaluation and project report writing; organize a special lecture project on current information technology facilitated by an expert in information technology both locally and internationally; principle of article writing; literature review; academic article writing and presenting articles on information technology."),
            Course("060243202", "โครงงานเทคโนโลยีสารสนเทศ 1", "Information Technology Project I", 3, "0-6-3", descriptionEn = "Information technology project; research; problem solving and applied information technology for government, business, and industry; project report documentation and required to pass an examination."),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, "")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 2 (โครงการปกติ)", listOf(
            Course("060243203", "โครงงานเทคโนโลยีสารสนเทศ 2", "Information Technology Project II", 3, "0-6-3", prerequisite = "060243202", descriptionEn = "Information technology project; research; problem solving and applied information technology for government, business, and industry; project report documentation and required to pass an examination."),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 1 (โครงการสหกิจศึกษา)", listOf(
            Course("060243120", "ความปลอดภัยระบบสารสนเทศ", "Information System Security", 3, "3-0-6", english = true, prerequisite = "None", descriptionEn = "Security techniques; type of threat; protection; access control; classic and modern encryption techniques; key encryption; message authentication and hash functions; digital signature and authentication; tools and techniques for information system security; computer security audit; risk assessment and management in information system security; computer-related crime act."),
            Course("060243121", "สัมมนาเทคโนโลยีสารสนเทศ", "Information Technology Seminar", 1, "0-3-1", prerequisite = "None", descriptionEn = "Seminar principle; seminar project writing, conducting seminars, evaluation and project report writing; organize a special lecture project on current information technology facilitated by an expert in information technology both locally and internationally; principle of article writing; literature review; academic article writing and presenting articles on information technology."),
            Course("060243303", "เตรียมสหกิจศึกษา", "Co-operative Education Preparation", 1, "0-2-1", prerequisite = "None", descriptionEn = "Principle, concept and philosophy of co-operative education; process and regulation related to co-operative education; job recruitment technique and interview; basic knowledge in the workplace; interpersonal self-development based on competency IT standard; social adaptation; personality development; information technology for personal communication; human relation; teamwork; organization structure; office administration; labor law; quality management in organization; professional and ethical skill enhancement; project implementation guideline; daily report; project report writing; presentation project."),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาชีพ", "Professional Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 2 (โครงการสหกิจศึกษา)", listOf(
            Course("060243302", "สหกิจศึกษา", "Co-operative Education", 6, "540 ชั่วโมง", prerequisite = "060243303", descriptionEn = "Students are required for co-operative education and complete a project at the company, with approval from the department; The duration of the co-operative education should be not less than 18 weeks or equivalent to 540 hours")
        ))
    )

    val itTransferTerms = listOf(
        Term("ปีที่ 1 · ภาคเรียนที่ 1", listOf(
            Course("060243102", "การโปรแกรมคอมพิวเตอร์", "Computer Programming", 3, "2-2-5"),
            Course("060243105", "ความรู้พื้นฐานในการออกแบบกราฟิก", "Fundamental of Graphics Design", 3, "2-2-5"),
            Course("060243108", "ระบบฐานข้อมูล", "Database System", 3, "3-0-6", english = true),
            Course("060243124", "ธุรกิจดิจิทัลเบื้องต้น", "Fundamental of Digital Business", 3, "3-0-6"),
            Course("080103063", "การใช้ภาษาอังกฤษ", "Practical English", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 1 · ภาคเรียนที่ 2", listOf(
            Course("060243104", "การเขียนโปรแกรมเชิงวัตถุ", "Object-oriented Programming", 3, "2-2-5", english = true),
            Course("060243115", "การออกแบบจากประสบการณ์ผู้ใช้", "User Experience Design", 3, "2-2-5", english = true),
            Course("060243122", "เว็บแอปพลิเคชัน", "Web Application", 3, "2-2-5"),
            Course("060243112", "การวิเคราะห์และออกแบบระบบ", "System Analysis and Design", 3, "3-0-6"),
            Course("060243123", "เครือข่ายคอมพิวเตอร์และอินเทอร์เน็ต", "Computer Network and Internet", 3, "3-0-6"),
            Course("060243119", "", "Information Technology Project Management", 3, "3-0-6"),
            Course("060243708", "กระบวนการทางธุรกิจเชิงอิเล็กทรอนิกส์", "Electronic Business Process", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 1", listOf(
            Course("060243116", "อินเทอร์เน็ตในทุกสรรพสิ่ง", "Internet of Things", 3, "2-2-5", english = true),
            Course("060243106", "โครงสร้างข้อมูลและขั้นตอนวิธี", "Data Structure and Algorithm", 3, "3-0-6", english = true),
            Course("060243707", "การโปรแกรมคอมพิวเตอร์ทางธุรกิจ", "Business Programming", 3, "2-2-5"),
            Course("060243718", "การวิเคราะห์ธุรกิจ", "Business Analytics", 3, "3-0-6"),
            Course("060243706", "โปรแกรมประยุกต์การวางแผนทรัพยากรองค์กร", "Enterprise Resources Planning Application", 3, "2-2-5"),
            Course("060243118", "การพัฒนาโปรแกรมประยุกต์บนอุปกรณ์เคลื่อนที่", "Mobile Application Development", 3, "2-2-5"),
            Course("060243111", "วิศวกรรมซอฟต์แวร์", "Software Engineering", 3, "3-0-6", english = true)
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 2", listOf(
            Course("060243716", "หัวข้อเฉพาะเรื่องทางธุรกิจดิจิทัล", "Selected Topics in Digital Business", 3, "3-0-6"),
            Course("060243717", "การประยุกต์คอมพิวเตอร์เพื่องานธุรกิจ", "Computer Application for Business", 3, "3-0-6"),
            Course("060243705", "ระบบอัจฉริยะเชิงธุรกิจ", "Business Intelligent System", 3, "3-0-6"),
            Course("060243202", "โครงงานเทคโนโลยีสารสนเทศ 1", "Information Technology Project I", 3, "0-6-3"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        ))
    )

    val ineTransferTerms = listOf(
        Term("ปีที่ 1 · ภาคเรียนที่ 1", listOf(
            Course("040203123", "คณิตศาสตร์เต็มหน่วยและการประยุกต์", "Discrete Mathematics and Applications", 3, "3-0-6"),
            Course("060233101", "วิศวกรรมสารสนเทศและเครือข่ายเบื้องต้น", "Introduction to Information and Network Engineering", 3, "2-2-5"),
            Course("060233108", "การสื่อสารข้อมูลและเครือข่ายคอมพิวเตอร์", "Data Communication and Computer Network", 3, "3-0-6"),
            Course("060233115", "การเขียนโปรแกรมคอมพิวเตอร์", "Computer Programming", 3, "2-2-5"),
            Course("080103063", "การใช้ภาษาอังกฤษ", "Practical English", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 1 · ภาคเรียนที่ 2", listOf(
            Course("060233106", "การเขียนแบบวิศวกรรมสารสนเทศและเครือข่าย", "Information and Network Engineering Drawing", 3, "2-2-5"),
            Course("060233111", "สถาปัตยกรรมคอมพิวเตอร์และระบบปฏิบัติการ", "Computer Architecture and Operating System", 3, "3-0-6", english = true),
            Course("060233114", "สถิติสำหรับวิศวกรข้อมูลและนักวิทยาศาสตร์ข้อมูล", "Statistics for Data Engineers and Scientists", 3, "3-0-6"),
            Course("060233116", "การแก้ปัญหาและขั้นตอนวิธี", "Problem Solving and Algorithm", 3, "2-2-5"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 1", listOf(
            Course("060233120", "การพัฒนาระบบสารสนเทศแบบครบองค์ประกอบ", "Full Stack System Development", 3, "2-2-5"),
            Course("060233205", "เครือข่ายขั้นสูงและโปรโตคอล", "Advanced Network and Protocol", 3, "3-0-6", english = true),
            Course("060233207", "การประยุกต์ใช้การเรียนรู้ของเครื่องจักร", "Applied Machine Learning", 3, "2-2-5"),
            Course("060233215", "ปฏิบัติการวิศวกรรมสารสนเทศและเครือข่าย 1", "Information and Network Engineering Laboratory I", 1, "0-3-1", english = true),
            Course("060233218", "โครงสร้างพื้นฐานคลาวด์สำหรับซอฟต์แวร์และบริการ", "Cloud Infrastructure for Software and Service", 3, "2-2-5", english = true),
            Course("เลือกเรียน", "วิชาเลือกกลุ่มวิชาชีพ", "Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 2", listOf(
            Course("060233112", "วิศวกรรมข้อมูล", "Data Engineering", 3, "3-0-6"),
            Course("060233119", "ระบบฐานข้อมูล", "Database System", 3, "3-0-6"),
            Course("060233204", "การออกแบบและการจัดทำเครือข่ายคอมพิวเตอร์", "Computer Network Design and Implementation", 3, "3-0-6"),
            Course("060233209", "การพัฒนาโปรแกรมประยุกต์บนอุปกรณ์เคลื่อนที่", "Mobile Application Development", 3, "3-0-6"),
            Course("060233214", "สัมมนาวิศวกรรมสารสนเทศและเครือข่าย", "Information and Network Engineering Seminar", 1, "0-3-1", english = true),
            Course("060233216", "ปฏิบัติการวิศวกรรมสารสนเทศและเครือข่าย 2", "Information and Network Engineering Laboratory II", 1, "0-3-1", english = true),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคฤดูร้อน", listOf(
            Course("060233403", "การฝึกงาน", "Internship", 1, "240 ชั่วโมง")
        )),
        Term("ปีที่ 3 · ภาคเรียนที่ 1", listOf(
            Course("060233118", "วิศวกรรมซอฟต์แวร์", "Software Engineering", 3, "3-0-6"),
            Course("060233208", "ความมั่นคงปลอดภัยไซเบอร์", "Cybersecurity", 3, "3-0-6", english = true),
            Course("060233217", "ปฏิบัติการวิศวกรรมสารสนเทศและเครือข่าย 3", "Information and Network Engineering Laboratory III", 1, "0-3-1", english = true),
            Course("060233220", "การประมวลผลภาษาธรรมชาติ", "Natural Language Processing", 3, "2-2-5"),
            Course("060233221", "ความปลอดภัยและความเป็นส่วนตัวของข้อมูล", "Data Security and Privacy", 3, "3-0-6", english = true),
            Course("เลือกเรียน", "วิชาเลือกกลุ่มวิชาชีพ", "Elective Course", 3, "3-0-6"),
            Course("060233404", "การเตรียมโครงงานพิเศษวิศวกรรมสารสนเทศและเครือข่าย", "Information and Network Engineering Special Project", 1, "0-3-1")
        )),
        Term("ปีที่ 3 · ภาคเรียนที่ 2", listOf(
            Course("060233206", "การจัดการเครือข่ายเบื้องต้นและเครื่องมือ", "Fundamental of Network Management and Tools", 3, "3-0-6"),
            Course("060233212", "การวิเคราะห์ข้อมูลขนาดใหญ่", "Big Data Analytics", 3, "2-2-5", english = true),
            Course("060233219", "การออกแบบและการประยุกต์อินเทอร์เน็ตในทุกสรรพสิ่ง", "Internet of Things Design and Application", 3, "2-2-5", english = true),
            Course("060233405", "โครงงานพิเศษวิศวกรรมสารสนเทศและเครือข่าย", "Information and Network Engineering Special Project", 3, "0-6-3"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6")
        ))
    )

    val ine4Terms = listOf(
        Term("ปีที่ 1 · ภาคเรียนที่ 1", listOf(
            Course("060233101", "วิศวกรรมสารสนเทศและเครือข่ายเบื้องต้น", "Introduction to Information and Network Engineering", 3, "2-2-5"),
            Course("060233115", "การเขียนโปรแกรมคอมพิวเตอร์", "Computer Programming", 3, "2-2-5"),
            Course("080103001", "ภาษาอังกฤษ 1", "English I", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในชุดวิชากีฬาและนันทนาการ", "Sports and Recreations Elective Course", 1, "0-2-1"),
            Course("080303701", "กระบวนการคิดเชิงออกแบบ", "Design Thinking", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 1 · ภาคเรียนที่ 2", listOf(
            Course("040203123", "คณิตศาสตร์เต็มหน่วยและการประยุกต์", "Discrete Mathematics and Applications", 3, "3-0-6"),
            Course("060233108", "การสื่อสารข้อมูลและเครือข่ายคอมพิวเตอร์", "Data Communication and Computer Network", 3, "3-0-6"),
            Course("060233116", "การแก้ปัญหาและขั้นตอนวิธี", "Problem Solving and Algorithm", 3, "2-2-5"),
            Course("060233117", "อิเล็กทรอนิกส์และวงจรดิจิทัล", "Electronics and Digital Circuit", 3, "2-2-5"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 2, "2-0-4"),
            Course("080103002", "ภาษาอังกฤษ 2", "English II", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 1", listOf(
            Course("040203111", "คณิตศาสตร์วิศวกรรม 1", "Engineering Mathematics I", 3, "3-0-6"),
            Course("060233120", "การพัฒนาระบบสารสนเทศแบบครบองค์ประกอบ", "Full Stack System Development", 3, "2-2-5"),
            Course("060233205", "เครือข่ายขั้นสูงและโปรโตคอล", "Advanced Network and Protocol", 3, "3-0-6", english = true),
            Course("060233215", "ปฏิบัติการวิศวกรรมสารสนเทศและเครือข่าย 1", "Information and Network Engineering Laboratory I", 1, "0-3-1", english = true),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 2", listOf(
            Course("040203112", "คณิตศาสตร์วิศวกรรม 2", "Engineering Mathematics II", 3, "3-0-6"),
            Course("060233106", "การเขียนแบบวิศวกรรมสารสนเทศและเครือข่าย", "Information and Network Engineering Drawing", 3, "2-2-5"),
            Course("060233111", "สถาปัตยกรรมคอมพิวเตอร์และระบบปฏิบัติการ", "Computer Architecture and Operating System", 3, "3-0-6", english = true),
            Course("060233112", "วิศวกรรมข้อมูล", "Data Engineering", 3, "3-0-6"),
            Course("060233119", "ระบบฐานข้อมูล", "Database System", 3, "3-0-6"),
            Course("060233216", "ปฏิบัติการวิศวกรรมสารสนเทศและเครือข่าย 2", "Information and Network Engineering Laboratory II", 1, "0-3-1", english = true),
            Course("060233219", "การออกแบบและการประยุกต์อินเทอร์เน็ตในทุกสรรพสิ่ง", "Internet of Things Design and Application", 3, "2-2-5", english = true)
        )),
        Term("ปีที่ 3 · ภาคเรียนที่ 1", listOf(
            Course("060233118", "วิศวกรรมซอฟต์แวร์", "Software Engineering", 3, "3-0-6"),
            Course("060233207", "การประยุกต์ใช้การเรียนรู้ของเครื่องจักร", "Applied Machine Learning", 3, "2-2-5"),
            Course("060233217", "ปฏิบัติการวิศวกรรมสารสนเทศและเครือข่าย 3", "Information and Network Engineering Laboratory III", 1, "0-3-1", english = true),
            Course("060233218", "โครงสร้างพื้นฐานคลาวด์สำหรับซอฟต์แวร์และบริการ", "Cloud Infrastructure for Software and Service", 3, "2-2-5", english = true),
            Course("060233221", "ความปลอดภัยและความเป็นส่วนตัวของข้อมูล", "Data Security and Privacy", 3, "3-0-6", english = true),
            Course("เลือกเรียน", "วิชาเลือกกลุ่มวิชาชีพ", "Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 3 · ภาคเรียนที่ 2", listOf(
            Course("060233114", "สถิติสำหรับวิศวกรข้อมูลและนักวิทยาศาสตร์ข้อมูล", "Statistics for Data Engineers and Scientists", 3, "3-0-6"),
            Course("060233204", "การออกแบบและการจัดทำเครือข่ายคอมพิวเตอร์", "Computer Network Design and Implementation", 3, "3-0-6"),
            Course("060233206", "การจัดการเครือข่ายเบื้องต้นและเครื่องมือ", "Fundamental of Network Management and Tools", 3, "3-0-6"),
            Course("060233209", "การพัฒนาโปรแกรมประยุกต์บนอุปกรณ์เคลื่อนที่", "Mobile Application Development", 3, "3-0-6"),
            Course("060233212", "การวิเคราะห์ข้อมูลขนาดใหญ่", "Big Data Analytics", 3, "2-2-5", english = true),
            Course("060233214", "สัมมนาวิศวกรรมสารสนเทศและเครือข่าย", "Information and Network Engineering Seminar", 1, "0-3-1", english = true),
            Course("080203914", "ผู้ประกอบการนวัตกรรม", "Innovative Technopreneurs", 3, "3-0-6")
        )),
        Term("ปีที่ 3 · ภาคฤดูร้อน (โครงการปกติ)", listOf(
            Course("060233403", "การฝึกงาน", "Internship", 1, "240 ชั่วโมง")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 1 (โครงการปกติ)", listOf(
            Course("060233208", "ความมั่นคงปลอดภัยไซเบอร์", "Cybersecurity", 3, "3-0-6", english = true),
            Course("060233220", "การประมวลภาษาธรรมชาติ", "Natural Language Processing", 3, "2-2-5"),
            Course("060233401", "โครงงานวิศวกรรมสารสนเทศและเครือข่าย 1", "Information and Network Engineering Project I", 3, "0-6-3"),
            Course("เลือกเรียน", "วิชาเลือกกลุ่มวิชาชีพ", "Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 2 (โครงการปกติ)", listOf(
            Course("060233402", "โครงงานวิศวกรรมสารสนเทศและเครือข่าย 2", "Information and Network Engineering Project II", 3, "0-6-3"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 1 (โครงการสหกิจศึกษา)", listOf(
            Course("060233208", "ความมั่นคงปลอดภัยไซเบอร์", "Cybersecurity", 3, "3-0-6", english = true),
            Course("060233220", "การประมวลภาษาธรรมชาติ", "Natural Language Processing", 3, "2-2-5"),
            Course("060233501", "เตรียมสหกิจศึกษา", "Co-operative Education Preparation", 1, "0-2-4"),
            Course("เลือกเรียน", "วิชาเลือกกลุ่มวิชาชีพ", "Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในหมวดวิชาศึกษาทั่วไป", "General Education Elective Course", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 4 · ภาคเรียนที่ 2 (โครงการสหกิจศึกษา)", listOf(
            Course("060233502", "สหกิจศึกษา", "Co-operative Education", 6, "540 ชั่วโมง")
        ))
    )

    val indTerms = listOf(
        Term("ปีที่ 1 · ภาคเรียนที่ 1", listOf(
            Course("060223130", "ทักษะคอมพิวเตอร์เบื้องต้น", "Computer Basic Skill", 1, "0-3-1"),
            Course("060223111", "ดิจิทัลอิเล็กทรอนิกส์", "Digital Electronics", 3, "3-0-6", english = true),
            Course("060223112", "ปฏิบัติการดิจิทัลอิเล็กทรอนิกส์", "Digital Electronics Laboratory", 1, "0-3-1"),
            Course("060223131", "การโปรแกรมโครงสร้าง", "Structural Programming", 2, "2-0-4", english = true),
            Course("060223114", "ปฏิบัติการโปรแกรมโครงสร้าง", "Structural Programming Laboratory", 1, "0-3-1"),
            Course("060223123", "ระบบฐานข้อมูล", "Database System", 3, "3-0-6", english = true),
            Course("080103061", "การใช้ภาษาอังกฤษ 1", "Practical English I", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาวิทยาศาสตร์และคณิตศาสตร์", "Mathematics and Science Elective Course", 3, "3-0-6")
        )),
        Term("ปีที่ 1 · ภาคเรียนที่ 2", listOf(
            Course("060223115", "การวิเคราะห์และออกแบบระบบ", "System Analysis and Design", 3, "3-0-6"),
            Course("060223132", "การโปรแกรมเชิงวัตถุ", "Object-oriented Programming", 2, "2-0-4"),
            Course("060223117", "ปฏิบัติการโปรแกรมเชิงวัตถุ", "Object-oriented Programming Laboratory", 1, "0-3-1"),
            Course("060223119", "โครงสร้างข้อมูลและขั้นตอนวิธี", "Data Structure and Algorithm", 3, "3-0-6"),
            Course("060223135", "สถาปัตยกรรมคอมพิวเตอร์และระบบปฏิบัติการ", "Computer Architecture and Operating System", 3, "3-0-6", english = true),
            Course("060223136", "การศึกษาโครงงานเทคโนโลยีสารสนเทศ", "Information Technology Project Study", 2, "0-4-2"),
            Course("080103062", "การใช้ภาษาอังกฤษ 2", "Practical English II", 3, "3-0-6"),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาบูรณาการ", "Integrated Elective Course", 3, "")
        )),
        Term("ปีที่ 1 · ภาคฤดูร้อน", listOf(
            Course("060223129", "การฝึกปฏิบัติงาน", "Training", 2, "240 ชั่วโมง")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 1", listOf(
            Course("060223124", "การสื่อสารข้อมูลและเครือข่ายคอมพิวเตอร์", "Data Communication and Computer Networks", 3, "3-0-6"),
            Course("060223133", "การพัฒนาเว็บแอปพลิเคชัน", "Web Application Development", 2, "2-0-4", english = true),
            Course("060223134", "ปฏิบัติการพัฒนาเว็บแอปพลิเคชัน", "Web Application Development Laboratory", 1, "0-3-1"),
            Course("060223137", "โครงงานพิเศษเทคโนโลยีสารสนเทศ", "Information Technology Special Project", 4, "0-8-4"),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาสังคมศาสตร์และมนุษยศาสตร์", "Social Sciences and Humanities Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือก", "Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "")
        )),
        Term("ปีที่ 2 · ภาคเรียนที่ 2", listOf(
            Course("060223126", "สัมมนาเทคโนโลยีสารสนเทศ", "Information Technology Seminar", 1, "0-3-1"),
            Course("060223127", "การพัฒนาโปรแกรมประยุกต์สำหรับสถาปัตยกรรมแบบโอเพ่นซอร์ส", "Application Program Development for Open-Source Architecture", 3, "3-0-6"),
            Course("060223128", "หัวข้อเฉพาะเรื่องทางเทคโนโลยีสารสนเทศเพื่ออุตสาหกรรม", "Selected topics in Information Technology for Industry", 3, "0-6-3"),
            Course("060223138", "อินเทอร์เน็ตในทุกสิ่งและระบบอัจฉริยะ", "Internet of Thing and Smart System", 3, "3-0-6", english = true),
            Course("060223139", "ปฏิบัติการอินเทอร์เน็ตในทุกสิ่งและระบบอัจฉริยะ", "Internet of Things and Smart System Laboratory", 1, "0-3-1"),
            Course("เลือกเรียน", "วิชาเลือกในกลุ่มวิชาภาษา", "Language Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือก", "Elective Course", 3, ""),
            Course("เลือกเรียน", "วิชาเลือกเสรี", "Free Elective Course", 3, "")
        ))
    )

    val programs: List<Program> by lazy {
        listOf(
            Program(
                id = "bsc_it_4y", degree = "วท.บ.", plan = "4 ปี", name = "เทคโนโลยีสารสนเทศ",
                degreeFull = "วิทยาศาสตรบัณฑิต (เทคโนโลยีสารสนเทศ) · วท.บ. (เทคโนโลยีสารสนเทศ)",
                entry = "รับ ม.6 / ปวช.", credits = 120, feePerTerm = 19000, semesters = 8, tone = Tone.IT,
                revision = "หลักสูตรปรับปรุง 2567",
                structure = listOf("หมวดวิชาศึกษาทั่วไป" to 25, "หมวดวิชาเฉพาะ" to 89, "หมวดวิชาเลือกเสรี" to 6),
                studyPlans = listOf(
                    StudyPlan("โครงการปกติ", "ฝึกงาน 240 ชม. + โครงงาน 2 ภาคเรียน"),
                    StudyPlan("สหกิจศึกษา", "ทำงานจริงในสถานประกอบการ 1 ภาคเรียน")
                ),
                terms = it67Terms
            ),
            Program(
                id = "bsc_it_transfer", degree = "วท.บ.", plan = "เทียบโอน 2 ปี", name = "เทคโนโลยีสารสนเทศ (เทียบโอน)",
                degreeFull = "วิทยาศาสตรบัณฑิต (เทคโนโลยีสารสนเทศ) · วท.บ. (เทคโนโลยีสารสนเทศ)",
                entry = "รับ ปวส. / อนุปริญญา พาณิชยกรรม / บริหารธุรกิจ", credits = 84, feePerTerm = 19000, semesters = 4,
                tone = Tone.IT, revision = "หลักสูตรปรับปรุง 2567",
                structure = listOf("หมวดวิชาศึกษาทั่วไป" to 12, "หมวดวิชาเฉพาะ" to 66, "หมวดวิชาเลือกเสรี" to 6),
                studyPlans = listOf(StudyPlan("โครงการปกติ", "เรียน 4 ภาคเรียน เน้นธุรกิจดิจิทัล + โครงงาน 1 ภาคเรียน")),
                terms = itTransferTerms
            ),
            Program(
                id = "beng_ine_4y", degree = "วศ.บ.", plan = "4 ปี", name = "วิศวกรรมสารสนเทศและเครือข่าย",
                degreeFull = "วิศวกรรมศาสตรบัณฑิต (วิศวกรรมสารสนเทศและเครือข่าย) · วศ.บ. (วิศวกรรมสารสนเทศและเครือข่าย)",
                entry = "รับ ม.6 ทุกสาย / ปวช. ทุกสาขา", credits = 125, feePerTerm = 25000, semesters = 8,
                tone = Tone.INE, revision = "หลักสูตรปรับปรุง 2567",
                structure = listOf("หมวดวิชาศึกษาทั่วไป" to 24, "หมวดวิชาเฉพาะ" to 95, "หมวดวิชาเลือกเสรี" to 6),
                studyPlans = listOf(
                    StudyPlan("โครงการปกติ", "ฝึกงาน 240 ชม. + โครงงาน 2 ภาคเรียน"),
                    StudyPlan("สหกิจศึกษา", "สหกิจศึกษา 540 ชม. ในปีที่ 4")
                ),
                terms = ine4Terms
            ),
            Program(
                id = "beng_ine_transfer", degree = "วศ.บ.", plan = "เทียบโอน 3 ปี", name = "วิศวกรรมสารสนเทศและเครือข่าย (เทียบโอน)",
                degreeFull = "วิศวกรรมศาสตรบัณฑิต (วิศวกรรมสารสนเทศและเครือข่าย) · วศ.บ. (วิศวกรรมสารสนเทศและเครือข่าย)",
                entry = "รับ ปวส. / อนุปริญญา ทุกสาขา", credits = 102, feePerTerm = 25000, semesters = 6,
                tone = Tone.INE, revision = "หลักสูตรปรับปรุง 2567",
                structure = listOf("หมวดวิชาศึกษาทั่วไป" to 12, "หมวดวิชาเฉพาะ" to 84, "หมวดวิชาเลือกเสรี" to 6),
                studyPlans = listOf(StudyPlan("โครงการปกติ", "ฝึกงาน 240 ชม. + โครงงานพิเศษ")),
                terms = ineTransferTerms
            ),
            Program(
                id = "bind_it_2y", degree = "อส.บ.", plan = "ต่อเนื่อง 2 ปี", name = "เทคโนโลยีสารสนเทศ (ต่อเนื่อง)",
                degreeFull = "อุตสาหกรรมศาสตรบัณฑิต (เทคโนโลยีสารสนเทศ) · อส.บ. (เทคโนโลยีสารสนเทศ)",
                entry = "รับ ปวส. ช่างอุตสาหกรรม / พาณิชยกรรม", credits = 78, feePerTerm = 19000, semesters = 4,
                tone = Tone.IND, revision = "หลักสูตรปรับปรุง 2566",
                structure = listOf("หมวดวิชาศึกษาทั่วไป" to 18, "หมวดวิชาเฉพาะ" to 54, "หมวดวิชาเลือกเสรี" to 6),
                studyPlans = listOf(StudyPlan("โครงการปกติ", "ฝึกปฏิบัติงาน 240 ชม. (ฤดูร้อนปี 1) + โครงงานพิเศษ")),
                terms = indTerms
            )
        )
    }
}
