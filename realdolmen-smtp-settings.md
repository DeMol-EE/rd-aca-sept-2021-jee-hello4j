1. make new outbound-remote sockets with these coordinates:

Server name: smtp.office365.com
Port: 587

2. generate an app-specific password here:

https://account.activedirectory.windowsazure.com/AppPasswords.aspx

3. add a new mail session under the mail subsystem:

name: outlook
jndi: java:/mail/outlook
from: your email address

4. further edit this mail session by creating a new mail server in its properties:

sockets: the outbound sockets created in step 1
username: your email
password: the generated app password
SSL: no
TLS: yes

5. inject the mail session through `@Resourse(name = "java:/mail/outlook") Session session`

(make sure you import session from the mail packages)
