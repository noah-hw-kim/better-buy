<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".




<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin1-url]
[![LinkedIn][linkedin-shield]][linkedin2-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
<!--   <a href="https://github.com/noah-hw-kim/better-buy">
    <img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/BetterBuy_MainPage.png" alt="Main" width="70%" height="70%">
  </a> -->

<h3 align="center">Better-Buy-App</h3>

  <p align="center">
    Use Better Buy to compare products and determine the best value product!
    <br />
    <a href="https://github.com/noah-hw-kim/better-buy"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/noah-hw-kim/better-buy">View Demo</a>
    ·
    <a href="https://github.com/noah-hw-kim/better-buy/issues">Report Bug</a>
    ·
    <a href="https://github.com/noah-hw-kim/better-buy/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->

<summary>Table of Contents</summary>
<ol>
  <li>
    <a href="#about-the-project">About The Project</a>
    <ul>
      <li><a href="#built-with">Built With</a></li>
    </ul>
  </li>
  <li>
    <a href="#getting-started">Getting Started</a>
    <ul>
      <li><a href="#prerequisites">Prerequisites</a></li>
      <li><a href="#installation">Installation</a></li>
    </ul>
  </li>
  <li><a href="#usage">Usage</a></li>
  <li><a href="#roadmap">Roadmap</a></li>
  <li><a href="#contributing">Contributing</a></li>
  <li><a href="#license">License</a></li>
  <li><a href="#contact">Contact</a></li>
  <li><a href="#acknowledgments">Acknowledgments</a></li>
</ol>




<!-- ABOUT THE PROJECT -->
## About The Project

[![Better Buy][product-screenshot]](https://github.com/noah-hw-kim/better-buy/blob/main/Image/BetterBuy_MainPage.png)
Project details
* User can add up to 4 items to compare.
* User can select the item unit type (volume, mass, or length) and unit (oz, pound, etc).
* User can select the item category
* User can search items in search history

<!-- add up to 4 items and the program shows the product with the better value for the price.
User can search the items compared -->
<!-- Here's a blank template to get started: To avoid retyping too much info. Do a search and replace with your text editor for the following: `noah-hw-kim`, `better-buy`, `twitter_handle`, `linkedin_username`, `email_client`, `email`, `project_title`, `project_description` -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With
FrontEnd
<ul>
  <li>JavaScript</li>
  <li>Bootstrap</li>
  <li>HTML</li>
  <li>CSS</li>
</ul>

BackEnd
<ul>
  <li>Java</li>
  <li>Spring Boot</li>
  <li>MongoDB</li>
  <li>Maven Project</li>
</ul>

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
1. <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java 17 version</a> (We're testing the later version) 
* Check java version (Windows/Mac/Linux OS)
  ```sh
  java -version
  ```
  
2. <a href="https://www.mongodb.com/docs/atlas/getting-started/">MongoDB Atalas</a>
3. Spring Boot Extension
* For VS code, it requires extensions "Extension Pack for Java" and "Spring Boot Extension Pack". <br>
[Installation instruction link](https://code.visualstudio.com/docs/java/java-spring-boot)
* For Intellij, no extensions required.

### Installation
1. Clone the repo
```sh
   git clone https://github.com/noah-hw-kim/better-buy.git
   ```

2. Create .env file in "resources" directory (Backend -> src -> main -> java -> com.orrijoa.BetterBuy -> resources)
3. Define .env file with your mongoDB cluster accordingly <br>
e.x.) MONGO_DATABASE="[Database_Name]" <br>
MONGO_USER="[User_Name]" <br>
MONGO_PASSWORD="[User_Password]" <br>
MONGO_CLUSTER="[Cluster_Name]"

<!-- 
1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo
   ```sh
   git clone https://github.com/noah-hw-kim/better-buy.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API in `config.js`
   ```js
   const API_KEY = 'ENTER YOUR API';
   ``` -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

### Set up the app
1. Run "BetterBuyApplication.java" in Backend directory (Backend -> src -> main -> java\com\orrijoa\BetterBuy)
* Intellij
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Intellij_BetterBuyApplication.png" alt="Intellij_BetterBuyApplication" width="60%" height="60%">
* VS code
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Vscode_BetterBuyApplication.png" alt="Vscode_BetterBuyApplication" width="60%" height="60%">

2. Run live server "main.html" in Frontend directory
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/" alt="Vscode_main_html" width="60%" height="60%">

3. Main Page
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/BetterBuy_MainPage.png" alt="Main" width="60%" height="60%">

### Compare items
1. Fill out a list of items' info in main page (Required - Name, Price, Amount, Unity Type, and Unit | Optional - Brand, Store, and Category)
2. Click compare button
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Compare.png" alt="Compare" width="60%" height="60%">

3. Compare result displayed
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Compare_result.png" alt="Compare_result" width="60%" height="60%">

### Search items
1. Enter keyword (name, brand, stroe, or category) in the search box next to "Searchy History"
2. Click search icon
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Search.png" alt="Search" width="60%" height="60%">

3. Search result displayed
<img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Search_result.png" alt="Search_result" width="60%" height="60%">


<!-- Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_ -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Prevent user to compare different Unit Type. e.x.) length vs volume
- [ ] Available search box to search with non-exact match
- [ ] Available to select the unit system in between imperial or metric by user and update the base unit accordingly 
- [ ] Available to update the base unit according to the categories selected by user
    - e.x.) current: base unit for mass for all categories = oz 
    - future plan: base unit for mass for Meat, Fish and Seafood's = lb
- [ ] Launch App version  

See the [open issues](https://github.com/noah-hw-kim/better-buy/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the APACHE-2.0 License. See `LICENSE.md` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Noah(Hyeonwoo) Kim - noah.hw.kim@gmail.com
Lanny Ngo - lannyngo@gmail.com

Project Link: [https://github.com/noah-hw-kim/better-buy](https://github.com/noah-hw-kim/better-buy)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* QUDT Libraries - [https://github.com/qudt/qudt-public-repo](https://github.com/qudt/qudt-public-repo)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/noah-hw-kim/better-buy.svg?style=for-the-badge
[contributors-url]: https://github.com/noah-hw-kim/better-buy/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/noah-hw-kim/better-buy.svg?style=for-the-badge
[forks-url]: https://github.com/noah-hw-kim/better-buy/network/members
[stars-shield]: https://img.shields.io/github/stars/noah-hw-kim/better-buy.svg?style=for-the-badge
[stars-url]: https://github.com/noah-hw-kim/better-buy/stargazers
[issues-shield]: https://img.shields.io/github/issues/noah-hw-kim/better-buy.svg?style=for-the-badge
[issues-url]: https://github.com/noah-hw-kim/better-buy/issues
[license-shield]: https://img.shields.io/github/license/noah-hw-kim/better-buy.svg?style=for-the-badge
[license-url]: https://github.com/noah-hw-kim/better-buy/blob/main/LICENSE.md
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin1-url]: https://linkedin.com/in/noah-hw-kim/
[linkedin2-url]: https://linkedin.com/in/ngolanny/
[product-screenshot]: https://github.com/noah-hw-kim/better-buy/blob/main/Image/BetterBuy_MainPage.png

