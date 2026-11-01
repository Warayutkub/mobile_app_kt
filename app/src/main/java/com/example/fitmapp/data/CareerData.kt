package com.example.fitmapp.data

data class Career(val name: String, val icon: String)

object CareerData {

    val it = listOf(
        Career("นักเทคโนโลยีสารสนเทศ", "ic_computer"),
        Career("นักวิชาการคอมพิวเตอร์", "ic_book"),
        Career("นักวิเคราะห์และออกแบบระบบงาน", "ic_gear"),
        Career("ผู้พัฒนาซอฟต์แวร์", "ic_code1"),
        Career("ผู้ดูแลระบบฐานข้อมูล", "ic_server"),
        Career("นักออกแบบกราฟิกและพัฒนาสื่อดิจิทัล", "ic_image"),
        Career("นักออกแบบและพัฒนาเกมคอมพิวเตอร์", "ic_code1"),
        Career("นักวิเคราะห์ข้อมูล", "ic_spark"),
        Career("นักวิทยาศาสตร์ข้อมูล", "ic_node"),
        Career("บุคลากรทางการศึกษา", "ic_cap"),
        Career("อาชีพอิสระ / อาชีพอื่น ๆ ที่เกี่ยวข้อง", "ic_user")
    )

    val ine = listOf(
        Career("วิศวกรระบบเครือข่าย", "ic_node"),
        Career("นักวิชาการคอมพิวเตอร์", "ic_computer"),
        Career("วิศวกรระบบคลาวด์", "ic_cloud"),
        Career("วิศวกรความมั่นคงปลอดภัยทางไซเบอร์", "ic_shield"),
        Career("วิศวกรระบบ", "ic_gear"),
        Career("นักวิทยาการข้อมูล", "ic_server"),
        Career("โปรแกรมเมอร์ / ผู้พัฒนาซอฟต์แวร์", "ic_code1"),
        Career("อาชีพอิสระ / อาชีพอื่น ๆ ที่เกี่ยวข้อง", "ic_user")
    )

    val ind = listOf(
        Career("ผู้บริหารไอที", "ic_briefcase"),
        Career("นักพัฒนาโปรแกรม", "ic_code1"),
        Career("ผู้ดูแลระบบ", "ic_server"),
        Career("ผู้ดูแลระบบเครือข่าย", "ic_node"),
        Career("นักเขียนโปรแกรม", "ic_code1"),
        Career("นักวิเคราะห์และออกแบบระบบ", "ic_gear"),
        Career("ผู้สอน / ฝึกอบรมด้านไอที", "ic_cap"),
        Career("ผู้สนับสนุนไอที (IT Support)", "ic_computer"),
        Career("พนักงานขายอุปกรณ์ไอที", "ic_wallet"),
        Career("นักวิชาการคอมพิวเตอร์", "ic_book"),
        Career("ที่ปรึกษาด้านไอที", "ic_spark"),
        Career("ผู้ประกอบการอิสระ", "ic_user")
    )
}
