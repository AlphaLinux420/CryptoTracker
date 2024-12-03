import {useEffect, useState} from "react";

function News() {
const [data,setData] = useState<any[]>([]);

  useEffect(() => {
    fetch("https://newsapi.org/v2/everything?q=Crypto&from=2024-11-02&sortBy=publishedAt&apiKey=717f7b6744904fdfb2994f9d0bda8e4f&language=en")
  }, []);

  return <div>News</div>;
}

export default News