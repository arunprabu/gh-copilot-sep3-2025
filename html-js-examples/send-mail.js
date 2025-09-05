// use nodejs send email
const nodemailer = require('nodemailer');

const sendMail = async (to, subject, body) => {
  // Configure your SMTP transport (example uses Gmail SMTP)
  const transporter = nodemailer.createTransport({
    host: 'smtp.gmail.com',
    port: 587,
    secure: false, // true for 465, false for other ports
    auth: {
      user: process.env.EMAIL_USER, // set your email in environment variable EMAIL_USER
      pass: process.env.EMAIL_PASS, // set your password in environment variable EMAIL_PASS
    },
  });

  const mailOptions = {
    from: `"Sender Name" <${process.env.EMAIL_USER}>`, // sender address
    to,
    subject,
    text: body,
  };

  try {
    const info = await transporter.sendMail(mailOptions);
    // 'info' contains information about the sent email, including 'messageId' which is the unique ID of the sent message
    console.log('Email sent successfully:', info.messageId);
  } catch (error) {
    console.error('Error sending email:', error);
  }
};

// Example usage -- fill it by having fake info 
if (require.main === module) {
  // Example usage -- fill it by having fake info 
  sendMail('a@g.com', 'Test Subject', 'This is a test email body.');
}
