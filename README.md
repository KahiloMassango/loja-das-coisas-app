<h1 >Loja Das Coisas</h1> 

![home](images/loja-das-coisas-banner.png)


## TL;DR
If you would like to use this application, fork the repository and link back to [Kahilo Massango](https://github.com/KahiloMassango) for proper credits. Thanks in Advance!


## 🧠 Motivation behind Developing this Application

Many people in Luanda still have to travel to physical stores to buy clothes, accessories and other products, facing traffic and crowds, which makes the experience tiring and time-consuming. At the same time, small and medium-sized retailers have little online presence compared to large brands, which limits their sales.
Our startup solves this problem by offering an intuitive and personalized platform that connects sellers and buyers efficiently.


## 🧐 Why Loja Das Coisas?

Loja Das Coisas is here to revolutionize online commerce in Luanda. We offer personalized and efficient experiences. We connect sellers and buyers on an intuitive and innovative platform.


## 👷🏼 Built With

This application is initially developed for **Native Android users**. Any Contributors are open if they are willing to contribute.

It's a multi-module app and uses MVVM architecture.

Modules:
- **App**: Brings everything together required for the app to function correctly. This includes UI scaffolding and navigation.
- **Feature**:
  - `:authentication` – Handles user login, registration, and authentication flows.
  - `:cart` – Manages the shopping cart, including item addition/removal and cart state.
  - `:checkout` – Responsible for the checkout process and payment integrations.
  - `:discover` – List categories by genders.
  - `:home` – Contains the home screen logic and UI elements.
  - `:newaddress` – Manages adding or editing new addresses for delivery.
  - `:orders` – Tracks order history and order details.
  - `:productdetail` – Displays detailed information for a selected product.
  - `:search` – Implements the product search functionality and filtering.
  - `:shop` – Lists products.
  - `:store` – Manages store or seller-related functionalities, like store details.
  - `:userprofile` – Provides user profile management, such as updating personal info.
- **Core**:
  - `:data`: Fetching app data from multiple sources, shared by different features.
  - `:core:ui`: UI components and resources used by feature modules, such as product card, dropdown menus, etc.
  - `:network`: Making network requests and handling responses from a remote data source.
  - `:datastore`: Storing persistent data using DataStore.
  - `:database`: Local database storage using Room.
  - `:model`: Model classes used throughout the app.


This project was built using these technologies.

- Kotlin
- Jetpack Compose
- Dagger-Hilt
- Datastore
- Retrofit
- Google Maps
- Material Design 3
- Room Database -
- Coil Compose
- Work Manager
- Gson


## 🤩 Features

- Customers can create an account and edit it.
- Customers can browse and buy products from any store.
- Customer can choose delivery or pick for an order.
- Cart & Checkout: Secure and smooth order placement.
- Location Services: Integrated map for delivery address selection.
- Sorting & Filtering: Advanced product search and categorization.
- Search for products.


## Some Screenshots


<table>
 <tr>
   <td align="center">Home</td>
   <td align="center">Home - Dark</td>
   <td align="center">Product Detail</td>
 </tr>
 <tr>
   <td width="33.33%">&nbsp;<img src="./images/home-screen.png" ></td>
   <td width="33.33%">&nbsp;<img src="./images/home-dark.png"></td>
   <td width="33.33%">&nbsp;<img src="images/product-detail.png"></td>
 </tr>



 <tr>
   <td align="center">Product Detail - Dark</td>
   <td align="center">Cart</td>
   <td align="center">Cart - Dark</td>
 </tr>
 <tr>
   <td width="33.33%">&nbsp;<img src="images/product-detail-dark.png"></td>
   <td width="33.33%">&nbsp;<img src="images/cart.png"></td>
   <td width="33.33%">&nbsp;<img src="images/cart-dark.png"></td>
 </tr>

</table>


## Contribute

This project is open for contributors and feel free to `fork` and make a `Pull Request` to the repo. If you feel any enhancements are required don't hesitate to open an issue.

Store CMS app [here](https://github.com/KahiloMassango/seller-app)

You can find the backend api [here](https://github.com/KahiloMassango/loja-das-coisas-api)


## Show your support

Follow me for more projects and don't forget to Give a ⭐ if you like this app!