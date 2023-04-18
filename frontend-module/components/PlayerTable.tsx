import { gameTypeToPrintableName } from '@/util/GameType';
import { Player } from '@/util/interfaces/Player';
import { FolderOpenOutlined } from '@ant-design/icons';
import { Button, Table } from 'antd';
import { useRouter } from 'next/router';

export const PlayerTable = ({ players, isAll }: { players: Player[]; isAll: boolean }) => {
  const router = useRouter();

  return (
    <Table dataSource={players} size="small" bordered pagination={false} scroll={{ y: 600 }} style={{ minHeight: 600 }}>
      <Table.Column title="ID" dataIndex="player_id" />
      <Table.Column dataIndex="name" title="Name" />
      {isAll && <Table.Column title="Spiel" render={(_, record: Player) => gameTypeToPrintableName(record.game_type)} />}
      <Table.Column title="KDA" render={(_, record: Player) => record.global_kda.toFixed(2)} />
      <Table.Column
        title="Aktionen"
        render={(_, record: Player) => (
          <Button size="small" type="primary" icon={<FolderOpenOutlined />} onClick={() => router.push(`/player/${record.player_id}`)} />
        )}
      />
    </Table>
  );
};
