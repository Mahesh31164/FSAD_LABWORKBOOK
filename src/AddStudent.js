import React, { useState, useEffect } from "react";
import axios from "axios";

function AddStudent({ selected, refresh }) {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: ""
  });

  useEffect(() => {
    if (selected) setStudent(selected);
  }, [selected]);

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const submit = (e) => {
    e.preventDefault();

    if (student.id) {
      axios.put(`http://localhost:8080/students/${student.id}`, student)
        .then(refresh);
    } else {
      axios.post("http://localhost:8080/students", student)
        .then(refresh);
    }

    setStudent({ name: "", email: "", course: "" });
  };

  return (
    <div>
      <h2>Add / Update Student</h2>
      <form onSubmit={submit}>
        <input name="name" value={student.name} onChange={handleChange} placeholder="Name" /><br/>
        <input name="email" value={student.email} onChange={handleChange} placeholder="Email" /><br/>
        <input name="course" value={student.course} onChange={handleChange} placeholder="Course" /><br/>
        <button>{student.id ? "Update" : "Add"}</button>
      </form>
    </div>
  );
}

export default AddStudent;