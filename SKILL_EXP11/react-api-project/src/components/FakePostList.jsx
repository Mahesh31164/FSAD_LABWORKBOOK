import { useEffect, useState } from "react";
import axios from "axios";

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [filtered, setFiltered] = useState([]);

  const fetchPosts = () => {
    axios.get("https://dummyjson.com/posts")
      .then(res => {
        setPosts(res.data.posts);
        setFiltered(res.data.posts);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const handleFilter = (e) => {
    const value = e.target.value;

    if (value === "all") {
      setFiltered(posts);
    } else {
      setFiltered(posts.filter(p => p.userId === Number(value)));
    }
  };

  return (
    <div>
      <h2>Fake API Posts</h2>

      <button onClick={fetchPosts}>Refresh</button>

      <select onChange={handleFilter}>
        <option value="all">All</option>
        <option value="1">User 1</option>
        <option value="2">User 2</option>
      </select>

      {filtered.map(post => (
        <div key={post.id}>
          <h4>{post.title}</h4>
          <p>{post.body}</p>
        </div>
      ))}
    </div>
  );
}

export default FakePostList;