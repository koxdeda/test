package com.example.kafkatest.fileReader

import com.example.kafkatest.exception.UnsupportedFileExtension
import com.example.kafkatest.exception.UnsupportedFileName
import com.example.kafkatest.repository.ClientProfileRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class FileReader(
    @Autowired
    private val clientProfileRepository: ClientProfileRepository,
    @Autowired
    private val env: Environment
) {

    fun readFile(): Boolean{
        val lineList = mutableListOf<String>()


        File(FILENAME)
            .useLines { lines -> lines.forEach { lineList.add(it) }}
        lineList.forEach { println(">  $it") }
        val fileTitle = lineList[0].split(";")
        val fileExtension = FILENAME.split(".")[1]
        val fileNameData = FILENAME.split(".")[0].split("\\").last().split("_")
        if (!checkFileExtension(fileExtension)) throw UnsupportedFileExtension("File extension $fileExtension is unsupported")
        if (!checkFileName(fileNameData)) throw UnsupportedFileName("File name ${fileNameData.joinToString("_")} is unsupported")
        if (!checkFileTitle(fileTitle)) return false
//        for (i in 1 until lineList.size){
//            val currentLine = lineList[i].split(";")
//            if (currentLine.size > fileTitle.size) return "Unknown value column in line $i"
//            val client = ClientDto(
//                loyaltyProgram = currentLine[0],
//                firstName = currentLine[1],
//                lastName = currentLine[2],
//                middleName = currentLine[3],
//                email = null,
//                dateOfBirth = null,
//                sex = null,
//                phone = currentLine[4]
//            )
//            clientProfileRepository.createClient(client)
//        }

        return true
    }

    fun checkFileExtension(fileExtension: String): Boolean {
        val eventType: String = env.getProperty("eventType.$fileExtension")
            ?:  return false
        return true
    }
    fun checkFileName(fileNameData: List<String>): Boolean{
        if (fileNameData[0] != "VTB"
            || fileNameData[1] !=  LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))){
            return false
        }
        return true
    }


    fun checkFileTitle(fileTitle: List<String>): Boolean{
        return (fileTitle.contains(LOYALTY)
                && fileTitle.contains(FIRST_NAME)
                && fileTitle.contains(LAST_NAME)
                && fileTitle.contains(PHONE))
    }



    companion object{
        const val LOYALTY = "loyalty_program"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val PHONE = "phone"
        const val FILENAME = "C:\\Users\\79119\\IdeaProjects\\kafka-test\\src\\main\\kotlin\\com\\example\\kafkatest\\fileReader\\VTB_01032023_1.regB"
    }

}