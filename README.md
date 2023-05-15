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
    <img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/BetterBuy_MainPage.png" alt="Main" width="60%" height="60%">
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
https://github.com/noah-hw-kim/better-buy/assets/97554088/e1b0e953-1c7f-48cf-b6a8-166db775ec55

<ul>
  <li>User can add up to 4 items to compare.</li>
  <li>User can select the item unit type (volume, mass, or length) and unit (oz, pound, etc).</li>
  <li>User can select the item category</li>
  <li>User can search items in search history</li>
</ul>

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
There are two methods to run this app:
1. IntelliJ (Back-end) + VS Code (Front-end)
2. VS Code (Back-end + Front-end)


For both methods:
1. Check that you have <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java SE 17</a> installed (Windows/Mac/Linux OS).
  ```sh
  java -version
  ```
2. Install <a href="https://www.mongodb.com/docs/atlas/getting-started/">MongoDB Atalas</a>.
3. Install the VS Code extension: "Live Server (Five Server)" by Yannick


Additional prerequisite for Method #2 (VS Code Back-end + Front-end):
1. Install Spring Boot Extensions: ["Extension Pack for Java" and "Spring Boot Extension Pack"](https://code.visualstudio.com/docs/java/java-spring-boot). <br>


### Installation
1. Clone the repo.
```sh
   git clone https://github.com/noah-hw-kim/better-buy.git
   ```

2. Create .env file in "resources" directory (Backend -> src -> main -> java -> com.orrijoa.BetterBuy -> resources).
3. Define .env file with your mongoDB cluster accordingly: <br>
MONGO_DATABASE="[Database_Name]"<br>
MONGO_USER="[User_Name]"<br>
MONGO_PASSWORD="[User_Password]"<br>
MONGO_CLUSTER="[Cluster_Name]"

<!-- 
1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo.
   ```sh
   git clone https://github.com/noah-hw-kim/better-buy.git
   ```
3. Install NPM packages.
   ```sh
   npm install
   ```
4. Enter your API in `config.js`.
   ```js
   const API_KEY = 'ENTER YOUR API';
   ``` -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

### Set up the app: Method #1 (IntelliJ Back-end + VS Code Front-end)
1. Back-end: Run server via IntelliJ (Run the "BetterBuyApplication.java" file located inside: Backend -> src -> main -> java -> com.orrijoa.BetterBuy).<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Intellij_BetterBuyApplication.png" alt="Intellij_BetterBuyApplication" width="65%" height="65%">
2. Front-end: Run "main.html" via VS Code's Live Server extension.<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Vscode_main_html.png" alt="Vscode_main_html" width="50%" height="50%">

### Set up the app: Method #2 (VS Code Back-end + Front-end)
1. Back-end: Run server via VS Code<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Vscode_BetterBuyApplication.png" alt="Vscode_BetterBuyApplication" width="30%" height="30%">.
2. Front-end: Run "main.html" via VS Code's Live Server extension.<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Vscode_main_html.png" alt="Vscode_main_html" width="50%" height="50%">

### Compare items
1. Fill out a list of items info in main page (Required: Name, Price, Amount, Unity Type, and Unit; Optional: Brand, Store, and Category)
2. Click 'Compare' button<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Compare.png" alt="Compare" width="90%" height="90%">
3. See comparison result displayed<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Compare_result.png" alt="Compare_result" width="90%" height="90%">

### Search items
1. Enter keyword (name, brand, store, or category) in the search box next to "Searchy History".
2. Click search icon.<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Search.png" alt="Search">
3. See search result displayed.<br><img src="https://github.com/noah-hw-kim/better-buy/blob/main/Image/Search_result.png" alt="Search_result">


<!-- Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_ -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Prevent user from comparing different unit types. (e.x., length vs volume)
- [ ] Allow wildcard search in Search History
- [ ] Allow section between imperial or metric units (and update base unit accordingly)
- [ ] Update base unit according to the categories selected by user (e.x., current: base unit for mass for all categories = oz --> update: base unit for mass for Meat, Fish and Seafood's = lb, base unit for grains = oz, etc.)
- [ ] Launch iPhone and Android App versions
- [ ] Allow URL link entry and webpage parsing to complete item inputs

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

Noah (Hyeonwoo) Kim - noah.hw.kim@gmail.com
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

