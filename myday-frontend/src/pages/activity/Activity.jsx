import { Button, Card, Table, Space, message, Modal } from "antd";
import {
  PlusOutlined,
  ExclamationCircleOutlined,
  DeleteOutlined,
} from "@ant-design/icons";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { reqDeleteActivity, reqUserActivities } from "../../api/";

const title = "My activity";

function Activity() {
  const [activities, setActivities] = useState([]);
  // Activity to edit
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const extra = (
    <Button
      type="primary"
      style={{ margin: 0 }}
      onClick={() => navigate("/activity/create")}
    >
      <PlusOutlined />
      Add
    </Button>
  );
  const fetchActivities = async () => {
    setLoading(true);
    const activities = await reqUserActivities();
    activities.forEach((activity) => {
      activity.createdOn =
        activity.createdOn[0] +
        "-" +
        activity.createdOn[1] +
        "-" +
        activity.createdOn[2];
    });
    setActivities(activities);
    setLoading(false);
  };

  useEffect(() => {
    fetchActivities();
  }, []);

  const deleteActivity = (id) => {
    const deleteReq = async (id) => {
      try {
        const response = await reqDeleteActivity(id);
        console.log("Delete category resopnse: ", response);
        fetchActivities();
      } catch (err) {
        message.error(err.message || err);
      }
    };
    Modal.confirm({
      title: "Do you want to delete this activity?",
      icon: <ExclamationCircleOutlined />,
      onOk() {
        deleteReq(id);
      },
      onCancel() {},
    });
  };

  const columns = [
    {
      title: "Date",
      dataIndex: "createdOn",
      key: "createdOn",
      sorter: (a, b) => a.createdOn.localeCompare(b.createdOn),
    },
    {
      title: "Calories intake",
      dataIndex: "caloriesIn",
      key: "caloriesIn",
      sorter: (a, b) => a.caloriesIn - b.caloriesIn,
    },
    {
      title: "Calories burnt",
      dataIndex: "caloriesOut",
      key: "caloriesOut",
      sorter: (a, b) => a.caloriesOut - b.caloriesOut,
    },
    {
      title: "Comment",
      dataIndex: "comment",
      key: "comment",
      sorter: (a, b) => a.comment.length - b.comment.length,
    },
    {
      title: "Action",
      key: "action",
      render: (_, activity) => (
        <Space size="small">
          <DeleteOutlined
            onClick={() => deleteActivity(activity.id)}
            style={{ margin: 0, padding: 0 }}
          />
        </Space>
      ),
    },
  ];

  return (
    <Card title={title} extra={extra}>
      <Table
        boarderd
        rowKey="id"
        loading={loading}
        dataSource={activities}
        columns={columns}
        pagination={{ defaultPageSize: 5, showQuickJumper: true }}
      />
    </Card>
  );
}

export default Activity;
