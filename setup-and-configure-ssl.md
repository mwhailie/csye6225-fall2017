# setup-and-configure-ssl

## 1. Generating a CSR on Amazon Web Services (AWS)

The openssl command to generate a private key:
```
openssl genrsa 2048 > private-key.pem
```
2048 is a key size. AWS also supports 4096-bit encryption.

For private-key.pem specify your own key name in order to identify it later during installation.

The CSR is generated based on this private key. The following command is used for the CSR creation:
```
openssl req -new -key private-key.pem -out csr.pem
```

The output will look similar to the following example:
```
You are about to be asked to enter information that will be incorporated into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields, but you can leave some blank.
For some fields there will be a default value.
If you enter ‘.’, the field will be left blank.
```

The following information needs to be filled in.
```
Country Name (2 letter code) [AU]:
State or Province Name (full name) [Some-State]:
Locality Name (eg, city) []:Boston
Organization Name (eg, company) [Internet Widgits Pty Ltd]:
Organizational Unit Name (eg, section) []:
Common Name (e.g. server FQDN or YOUR name) []:
Email Address []:

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:
An optional company name []:
```

In the output you will see the CSR in plain text. You can use the following command to see the CSR
```
cat csr.pem 
```

## 2. Activate the SSL Certificates

Use Namecheap get one year SSL certificate for free with Github Student Developer pack.

## 3. Create the CNAME record manually at AWS DNS route53
