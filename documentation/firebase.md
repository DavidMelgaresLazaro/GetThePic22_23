# FIREBASE


*Recovery Password*: En el layout del login hem posat un boto que t'envia al layout on pots recuperar la contrasenya, introduïnt un correu existen en la base de dades(que estigui registrat).
L'activity "ForgotPasswordActivity", que quan introduiexes el email i prens al boto SUBMIT, mira que el correu estigui en la BD, si esta el correu associat a un compte et surtira un toast, amb aquest missatge: "Password send to your email" i s'envia un correu al email, per a realitzar el canvi de contrasenya; i si no esta asociat, et sortira un toast amb un missatge d'error. Tot aixo es fa gracies al firebaseAuth, que ens facilita Firebase.
