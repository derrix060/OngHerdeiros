package com.example.mario.ongproject.model

import java.util.*
import javax.activation.CommandMap
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.activation.MailcapCommandMap
import javax.mail.Multipart
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

/**
 * Created by mario on 14/05/2017.
 */

class Mail : javax.mail.Authenticator() {
    private val _user: String
    private val _pass: String

    private var _to: Array<String>? = null
    private val _from: String

    private val _port: String
    private val _sport: String

    private val _host: String

    private var _subject: String? = null
    private var _body: String? = null

    private val _auth: Boolean

    private val _debuggable: Boolean

    private val _multipart: Multipart

    init {
        _host = "smtp.gmail.com" // default smtp server
        _port = "465" // default smtp port
        _sport = "465" // default socketfactory port

        _pass = "H9!5kzc^33fFQW^KgkNg" // password
        _from = "ongherdeirosapp@gmail.com" // email sent from
        _user = _from // username
        this.set_to(_from)
        _subject = "" // email subject
        _body = "" // email body
        _subject = "Contato para apadrinhar - app"

        _debuggable = false // debug mode on or off - default off
        _auth = true // smtp authentication - default on

        _multipart = MimeMultipart()

        // There is something wrong with MailCap, javamail can not find a
        // handler for the multipart/mixed part, so this bit needs to be added.
        val mc = CommandMap
                .getDefaultCommandMap() as MailcapCommandMap
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html")
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml")
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain")
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed")
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822")
        CommandMap.setDefaultCommandMap(mc)
    }


    @Throws(Exception::class)
    fun send(): Boolean {
        val props = _setProperties()

        if (_user != "" && _pass != "" && _to!!.size > 0
                && _from != "" && _subject != ""
                && _body != "") {
            val session = Session.getInstance(props, this)

            val msg = MimeMessage(session)

            msg.setFrom(InternetAddress(_from))

            val addressTo = arrayOfNulls<InternetAddress>(_to!!.size)
            for (i in _to!!.indices) {
                addressTo[i] = InternetAddress(_to!![i])
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo)

            msg.subject = _subject
            msg.sentDate = Date()

            // setup message body
            val messageBodyPart = MimeBodyPart()
            messageBodyPart.setText(_body)
            _multipart.addBodyPart(messageBodyPart)

            msg.setHeader("X-Priority", "1")
            // Put parts in message
            msg.setContent(_multipart)

            // send email
            Transport.send(msg)

            return true
        } else {
            return false
        }
    }

    @Throws(Exception::class)
    fun addAttachment(filename: String) {
        val messageBodyPart = MimeBodyPart()
        val source = FileDataSource(filename)
        messageBodyPart.dataHandler = DataHandler(source)
        messageBodyPart.fileName = filename

        _multipart.addBodyPart(messageBodyPart)
    }

    public override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(_user, _pass)
    }

    private fun _setProperties(): Properties {
        val props = Properties()

        props.put("mail.smtp.host", _host)

        if (_debuggable) {
            props.put("mail.debug", "true")
        }

        if (_auth) {
            props.put("mail.smtp.auth", "true")
        }

        props.put("mail.smtp.port", _port)
        props.put("mail.smtp.socketFactory.port", _sport)
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory")
        props.put("mail.smtp.socketFactory.fallback", "false")

        return props
    }

    fun setBody(_body: String) {
        this._body = _body
    }

    private fun set_to(_to: String) {
        val __to: Array<String> = arrayOf(_to)
        this._to = __to
    }
}
